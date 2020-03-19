package com.soft.one.ewms.security.oauth2.utlis;

import java.io.Serializable;

/**
 * Title：令牌DTO
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/11
 */
public class AuthToken implements Serializable {

    //令牌信息
    String accessToken;

    //刷新token(refresh_token)
    String refreshToken;

    //jwt短令牌
    String jti;

    //是否修改密码 0为false,1为true
    Integer isRemind;

    public Integer getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(Integer isRemind) {
        this.isRemind = isRemind;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
