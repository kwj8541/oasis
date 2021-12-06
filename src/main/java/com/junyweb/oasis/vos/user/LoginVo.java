package com.junyweb.oasis.vos.user;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.user.HaveTicketResult;
import com.junyweb.oasis.enums.user.LoginResult;
import com.junyweb.oasis.utils.CryptoUtil;

public class LoginVo extends UserEntity {
    private LoginResult loginResult;
    private HaveTicketResult haveTicketResult;
    private String hashedPassword;
    private UserEntity userEntity;

    public HaveTicketResult getHaveTicketResult() {
        return haveTicketResult;
    }

    public void setHaveTicketResult(HaveTicketResult haveTicketResult) {
        this.haveTicketResult = haveTicketResult;
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

    public String getPassword() {
        return this.hashedPassword;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.hashedPassword = CryptoUtil.Sha512.hash(password);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
