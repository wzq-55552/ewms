package com.soft.one.ewms.security.oauth2.service.impl;

import com.soft.one.ewms.domain.mappers.user.LogInMapper;
import com.soft.one.ewms.domain.pojos.user.LogIn;
import com.soft.one.ewms.security.oauth2.service.LogInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LogInServiceImpl implements LogInService {

    @Resource
    private LogInMapper logInMapper;

    @Override
    public int insert(LogIn logIn) {
        return logInMapper.insert(logIn);
    }

    @Override
    public LogIn selectByUserIdAndOutDate(String userId) {
        return logInMapper.selectByUserIdAndOutDate(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(LogIn logIn) {
        return logInMapper.updateByPrimaryKeySelective(logIn);
    }

}
