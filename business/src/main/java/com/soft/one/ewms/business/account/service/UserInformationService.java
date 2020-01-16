package com.soft.one.ewms.business.account.service;


import com.soft.one.ewms.domain.UserInformation;


public interface UserInformationService{

    UserInformation login(String userId, String userPsw);
    void updateinformation(UserInformation userInformation);
    void setuserinfo(String userid,String username,String userPsw);
    UserInformation selectByUserid(String userid);

}
