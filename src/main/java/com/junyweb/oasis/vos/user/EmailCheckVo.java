package com.junyweb.oasis.vos.user;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.user.EmailCheckResult;

public class EmailCheckVo extends UserEntity {
    private EmailCheckResult emailCheckResult;

    public EmailCheckResult getEmailCheckResult() {
        return emailCheckResult;
    }

    public void setEmailCheckResult(EmailCheckResult emailCheckResult) {
        this.emailCheckResult = emailCheckResult;
    }
}
