package com.junyweb.oasis.vos.user;

import com.junyweb.oasis.entities.VerificationCodeEntity;
import com.junyweb.oasis.enums.user.EmailVerificationResult;

public class EmailVerificationVo extends VerificationCodeEntity {
    private EmailVerificationResult emailVerificationResult;

    public EmailVerificationResult getEmailVerificationResult() {
        return emailVerificationResult;
    }

    public void setEmailVerificationResult(EmailVerificationResult emailVerificationResult) {
        this.emailVerificationResult = emailVerificationResult;
    }
}
