package com.soft.one.ewms.business.account.service;


import com.soft.one.ewms.domain.UserInformation;

import javax.servlet.http.HttpServletRequest;


public interface UserInformationService{

    UserInformation selectByPrimaryKey(String userid);
    UserInformation login(String userId, String userPsw);
    void updateinformation(UserInformation userInformation);


}
