package com.soft.one.ewms.business.account.service.impl;

import com.soft.one.ewms.domain.TimeArgs;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.account.mapper.TimeArgsMapper;
import com.soft.one.ewms.business.account.service.TimeArgsService;
@Service
public class TimeArgsServiceImpl implements TimeArgsService{

    @Resource
    private TimeArgsMapper timeArgsMapper;

    @Override
    public void updateTimeArgs(TimeArgs timeArgs) {
        timeArgsMapper.updateTimeArgs(timeArgs);
    }

    @Override
    public TimeArgs selectTimeArgs() {
        return timeArgsMapper.selectTimeArgs();
    }
}
