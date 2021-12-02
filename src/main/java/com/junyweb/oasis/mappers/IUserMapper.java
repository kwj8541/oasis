package com.junyweb.oasis.mappers;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.entities.VerificationCodeEntity;
import com.junyweb.oasis.vos.user.LoginVo;
import com.junyweb.oasis.vos.user.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int insertUser(RegisterVo registerVo); // 회원가입 매퍼 메서드

    int insertVerificationCode(RegisterVo registerVo); // 회원가입시 인증코드 매퍼 메서드

    int selectEmailCount(@Param("email") String email); // 파라미터로 email 을 받고 같은 이메일이 있는지 확인하는 매퍼 메서드 -> 회원가입시 이메일 중복 확인하기위한 매퍼 메서드

    int selectNicknameCount(@Param("nickname") String nickname); // 파라미터로 nickname 을 받고 같은 닉네임이 있는지 확인하는 매퍼 메서드 -> 회원가입시 닉네임 중복 확인하기위한 매퍼 메서드

    UserEntity selectUserByEmailVerification(VerificationCodeEntity verificationCodeEntity);

    int updateEmailVerificationCodeExpired(VerificationCodeEntity verificationCodeEntity); // 이메일인증코드 만료 update 메서드

    int updateUser(UserEntity userEntity);

    UserEntity selectUserByLogin(LoginVo loginVo);
}
