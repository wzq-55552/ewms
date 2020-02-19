package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.LogInMapper;
import com.soft.one.ewms.business.user.service.LogInService;
@Service
public class LogInServiceImpl implements LogInService{

    @Resource
    private LogInMapper logInMapper;

}
