package com.junyweb.oasis.vos.user;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.user.NicknameCheckResult;

public class NicknameCheckVo extends UserEntity {
    private NicknameCheckResult nicknameCheckResult;

    public NicknameCheckResult getNicknameCheckResult() {
        return nicknameCheckResult;
    }

    public void setNicknameCheckResult(NicknameCheckResult nicknameCheckResult) {
        this.nicknameCheckResult = nicknameCheckResult;
    }
}
