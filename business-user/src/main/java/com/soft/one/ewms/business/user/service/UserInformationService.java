package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.UserInformation;

public interface UserInformationService{

    int updateByPrimaryKeySelective(UserInformation userInformation);

    int insert(UserInformation userInformation);

    UserInformation selectByPrimaryKey(String userId);

    int deleteByPrimaryKey(String userId);
}
