package com.soft.one.ewms.business.account.service;

import com.soft.one.ewms.domain.LogIn;
import com.soft.one.ewms.domain.UserInformation;

public interface UserInformationService{

    UserInformation selectByPrimaryKey(String userid);
    UserInformation login(String userId,String userPsw);

}
