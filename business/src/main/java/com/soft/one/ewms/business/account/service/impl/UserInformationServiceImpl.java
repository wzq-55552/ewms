package com.soft.one.ewms.business.account.service.impl;

import com.soft.one.ewms.domain.LogIn;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.soft.one.ewms.business.account.mapper.UserInformationMapper;
import com.soft.one.ewms.business.account.service.UserInformationService;

import java.util.Date;

@Service
public class UserInformationServiceImpl implements UserInformationService{

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    public UserInformation selectByPrimaryKey(String userid) {
        //通过userid查找账号密码
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


}
