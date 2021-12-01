package com.junyweb.oasis.vos.user;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.user.RegisterResult;
import com.junyweb.oasis.utils.CryptoUtil;

public class RegisterVo extends UserEntity {
    private RegisterResult registerResult;
    private String hashedPassword;
    private String verificationCode;

    public RegisterResult getRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(RegisterResult registerResult) {
        this.registerResult = registerResult;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.hashedPassword = CryptoUtil.Sha512.hash(password);
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
