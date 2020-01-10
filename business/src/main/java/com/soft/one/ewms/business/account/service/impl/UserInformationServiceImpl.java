package com.soft.one.ewms.business.account.service.impl;


import com.soft.one.ewms.domain.UserInformation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


import com.soft.one.ewms.business.account.mapper.UserInformationMapper;
import com.soft.one.ewms.business.account.service.UserInformationService;



@Service
public class UserInformationServiceImpl implements UserInformationService{

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    public UserInformation selectByPrimaryKey(String userid) {
        //通过userid查找Userinformation
        return userInformationMapper.selectByPrimaryKey(userid);
    }

    @Override
    public UserInformation login(String userId, String userPsw) {
        UserInformation userInformation = userInformationMapper.selectByPrimaryKey(userId);
        //判断输入的密码是否与数据库中的密码一致
        if(userPsw.equals(userInformation.getUserPsw()))
        {
            return userInformation;
        }
        return null;
    }

    @Override
    public void updateinformation(UserInformation userInformation) {
        userInformationMapper.updateinformation(userInformation);
    }

    @Override
    public void setuserinfo(UserInformation userInformation) {
        userInformationMapper.setuserinfo(userInformation);
    }


}
