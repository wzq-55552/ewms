package com.soft.one.ewms.security.oauth2.utlis;

import java.io.Serializable;

/**
 * Title：登录dto
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/18
 */
public class LoginDto implements Serializable {

    private static final long serialVersionUID = -5924360569223645433L;

    String userID;

    String userPsw;

    String ip;

    //验证码
    String randString;

    public String getRandString() {
        return randString;
    }

    public void setRandString(String randString) {
        this.randString = randString;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userId) {
        this.userID = userId;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
}
