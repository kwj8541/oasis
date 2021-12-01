package com.junyweb.oasis.mappers;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.entities.VerificationCodeEntity;
import com.junyweb.oasis.vos.user.LoginVo;
import com.junyweb.oasis.vos.user.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int insertUser(RegisterVo registerVo);

    int insertVerificationCode(RegisterVo registerVo);

    int selectEmailCount(@Param("email") String email);

    int selectNicknameCount(@Param("nickname") String nickname);

    UserEntity selectUserByEmailVerification(VerificationCodeEntity verificationCodeEntity);

    int updateEmailVerificationCodeExpired(VerificationCodeEntity verificationCodeEntity);

    int updateUser(UserEntity userEntity);

    UserEntity selectUserByLogin(LoginVo loginVo);
}
