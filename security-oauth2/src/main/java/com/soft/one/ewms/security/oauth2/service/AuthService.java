package com.soft.one.ewms.security.oauth2.service;

import com.soft.one.ewms.security.oauth2.utlis.AuthToken;
import com.soft.one.ewms.security.oauth2.utlis.LoginDto;

public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(LoginDto loginDto, String clientId, String clientSecret);
}
