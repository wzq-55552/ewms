package com.soft.one.ewms.security.oauth2.service.impl;

import com.soft.one.ewms.domain.mappers.user.TimeArgsMapper;
import com.soft.one.ewms.domain.pojos.user.TimeArgs;
import com.soft.one.ewms.security.oauth2.service.TimeArgsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TimeArgsServiceImpl implements TimeArgsService {

    @Resource
    private TimeArgsMapper timeArgsMapper;

    @Override
    public TimeArgs selectOneByLastedTime() {
        return timeArgsMapper.SelectOneByLastedTime();
    }

}
