package com.soft.one.ewms.security.oauth2.service;

import com.soft.one.ewms.domain.pojos.user.UserInformation;

public interface UserInformationService{

    /**
     * 根据用户id查询用户
     * @param s 用户id
     * @return
     */
    UserInformation getByUserId(String s);

}
