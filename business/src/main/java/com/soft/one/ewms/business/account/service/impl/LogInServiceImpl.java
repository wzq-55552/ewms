package com.soft.one.ewms.business.account.service.impl;

import com.soft.one.ewms.domain.LogIn;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.account.mapper.LogInMapper;
import com.soft.one.ewms.business.account.service.LogInService;



@Service
public class LogInServiceImpl implements LogInService{

    @Resource
    private LogInMapper logInMapper;


    @Override
    public void insertloginformation(LogIn login) {
        logInMapper.insert(login);
    }



    @Override
    public LogIn selectByUserid(String userid) {
        return logInMapper.selectByuserid(userid);
    }

    @Override
    public void deleteLoginfo(String userid) {
        logInMapper.deleteLoginfo(userid);
    }

    @Override
    public void updateloginfo(LogIn login) {
        logInMapper.updateloginfo(login);
    }
}
