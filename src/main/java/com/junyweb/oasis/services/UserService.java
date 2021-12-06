package com.junyweb.oasis.services;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.user.*;
import com.junyweb.oasis.mappers.IUserMapper;
import com.junyweb.oasis.utils.CryptoUtil;
import com.junyweb.oasis.vos.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UserService {

    public static class RegExp {    // 정규식 설정
        public static final String EMAIL = "^(?=.{8,50}$)([0-9a-z_]{4,})@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
        public static final String PASSWORD = "^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{8,50})$";
        public static final String NICKNAME = "^([0-9a-zA-Z가-힣]{1,10})$";
        public static final String NAME = "^([가-힣]{2,10})$";
        public static final String ADDRESS_POSTAL = "^([0-9]{5})$";
        public static final String ADDRESS_PRIMARY = "^([0-9a-zA-Z가-힣()\\-, ]{8,100})$";
        public static final String ADDRESS_SECONDARY = "^([0-9a-zA-Z가-힣()\\-, ]{0,100})$";

        public static final String EMAIL_VERIFICATION_CODE = "^([0-9a-z]{128})$";

        private RegExp() {
        }
    }

    private final JavaMailSender mailSender;
    private final IUserMapper userMapper;

    @Autowired
    public UserService(JavaMailSender mailSender, IUserMapper userMapper) {
        this.mailSender = mailSender;
        this.userMapper = userMapper;
    }

    public static boolean checkEmail(String s) {
        return s != null && s.matches(RegExp.EMAIL);
    }

    public static boolean checkPassword(String s) {
        return s != null && s.matches(RegExp.PASSWORD);
    }

    public static boolean checkNickname(String s) {
        return s != null && s.matches(RegExp.NICKNAME);
    }

    public static boolean checkName(String s) {
        return s != null && s.matches(RegExp.NAME);
    }

    public static boolean checkAddressPostal(String s) {
        return s != null && s.matches(RegExp.ADDRESS_POSTAL);
    }

    public static boolean checkAddressPrimary(String s) {
        return s != null && s.matches(RegExp.ADDRESS_PRIMARY);
    }

    public static boolean checkAddressSecondary(String s) {
        return s != null && s.matches(RegExp.ADDRESS_SECONDARY);
    }

    public static boolean checkEmailVerificationCode(String s) {
        return s != null && s.matches(RegExp.EMAIL_VERIFICATION_CODE);
    }

    // <<<<<<<<<<<< 회원가입 >>>>>>>>>>>>>>
    public void register(RegisterVo registerVo) throws MessagingException {
        // 정규식 체크
        if (!UserService.checkEmail(registerVo.getEmail()) ||
                !UserService.checkPassword(registerVo.getPassword()) ||
                !UserService.checkNickname(registerVo.getNickname()) ||
                !UserService.checkName(registerVo.getName()) ||
                !UserService.checkAddressPostal(registerVo.getAddressPostal()) ||
                !UserService.checkAddressPrimary(registerVo.getAddressPrimary()) ||
                !UserService.checkAddressSecondary(registerVo.getAddressSecondary())) {
            registerVo.setRegisterResult(RegisterResult.NORMALIZATION_FAILURE);
            return;
        }
        // 이메일 중복확인
        if (this.userMapper.selectEmailCount(registerVo.getEmail()) > 0) {
            registerVo.setRegisterResult(RegisterResult.EMAIL_DUPLICATE);
            return;
        }
        // 닉네임 중복확인
        if (this.userMapper.selectNicknameCount(registerVo.getNickname()) > 0) {
            registerVo.setRegisterResult(RegisterResult.NICKNAME_DUPLICATE);
            return;
        }

        // 회원가입 성공여부 확인
        if (this.userMapper.insertUser(registerVo) == 0) {
            registerVo.setRegisterResult(RegisterResult.FAILURE);
            return;
        }
        // 인증코드
        String verificationCode = CryptoUtil.Sha512.hash(String.format("%s%s%f%f",
                registerVo.getEmail(),
                registerVo.getHashedPassword(),
                Math.random(),
                Math.random()));
        registerVo.setVerificationCode(verificationCode);
        this.userMapper.insertVerificationCode(registerVo);

        // 이메일 인증
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(registerVo.getEmail()); // 회원가입한 이메일로 인증메일 전송
        helper.setSubject("OASIS 회원가입 인증 이메일"); // 인증 메일 제목
        helper.setText(String.format("<a href=\"http://127.0.0.1/email-verify?code=%s\" target=\"_blank\">인증하기</a>", verificationCode), true); // 인증 메일 내용
        this.mailSender.send(mimeMessage);

        registerVo.setRegisterResult(RegisterResult.SUCCESS);
    }

    public void checkEmail(EmailCheckVo emailCheckVo) { // 이메일 정규식 확인
        if (!UserService.checkEmail(emailCheckVo.getEmail())) {
            emailCheckVo.setEmailCheckResult(EmailCheckResult.NORMALIZATION_FAILURE);
            return;
        }
        emailCheckVo.setEmailCheckResult(this.userMapper.selectEmailCount(emailCheckVo.getEmail()) > 0 ?
                EmailCheckResult.EXISTING :
                EmailCheckResult.NON_EXISTING);
    }

    public void checkNickname(NicknameCheckVo nicknameCheckVo) { // 닉네임 정규식 확인
        if (!UserService.checkNickname(nicknameCheckVo.getNickname())) {
            nicknameCheckVo.setNicknameCheckResult(NicknameCheckResult.NORMALIZATION_FAILURE);
            return;
        }
        nicknameCheckVo.setNicknameCheckResult(this.userMapper.selectNicknameCount(nicknameCheckVo.getNickname()) > 0 ?
                NicknameCheckResult.EXISTING :
                NicknameCheckResult.NON_EXISTING);
    }

    public void verifyEmail(EmailVerificationVo emailVerificationVo){ // 이메일 인증 메서드
        if (!UserService.checkEmailVerificationCode(emailVerificationVo.getCode())) {
            emailVerificationVo.setEmailVerificationResult(EmailVerificationResult.FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUserByEmailVerification(emailVerificationVo);
        if (userEntity == null) {
            emailVerificationVo.setEmailVerificationResult(EmailVerificationResult.FAILURE);
            return;
        }
        userEntity.setEmailVerified(true);
        this.userMapper.updateEmailVerificationCodeExpired(emailVerificationVo);
        this.userMapper.updateUser(userEntity);
        emailVerificationVo.setEmailVerificationResult(EmailVerificationResult.SUCCESS);
    }

    public void login(LoginVo loginVo) {    // 로그인 메서드
        if (!UserService.checkEmail(loginVo.getEmail()) || UserService.checkPassword(loginVo.getPassword())) {
            loginVo.setLoginResult(LoginResult.NORMALIZATION_FAILURE);
            return;
        }

        loginVo.setUserEntity(this.userMapper.selectUserByLogin(loginVo)); // 로그인시 입력한 이메일의 회원정보 Entity 에 담기

        if (loginVo.getUserEntity() == null) {  // 입력한 이메일과 일치하는 회원이 없는경우
            loginVo.setLoginResult(LoginResult.FAILURE);
            return;
        }
        if (loginVo.getUserEntity().isDeleted()) {  // 입력한 이메일의 회원정보가 삭제된경우
            loginVo.setLoginResult(LoginResult.DELETED);
            return;
        }
        if (!loginVo.getUserEntity().isEmailVerified()) {   // 입력한 이메일의 이메일인증이 확인되지않은경우
            loginVo.setLoginResult(LoginResult.EMAIL_NOT_VERIFIED);
            return;
        }
        if (loginVo.getUserEntity().isSuspended()) {    // 입력한 이메일의 회원이 계정정지된경우
            loginVo.setLoginResult(LoginResult.SUSPENDED);
            return;
        }

        if (!loginVo.isHaveTicket()) {
            loginVo.setHaveTicketResult(HaveTicketResult.FALSE);
        }
        loginVo.setHaveTicketResult(HaveTicketResult.TRUE);
        loginVo.setLoginResult(LoginResult.SUCCESS);
    }
}














