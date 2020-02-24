package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.TimeArgs;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.TimeArgsMapper;
import com.soft.one.ewms.business.user.service.TimeArgsService;

import java.util.List;

@Service
public class TimeArgsServiceImpl implements TimeArgsService{

    @Resource
    private TimeArgsMapper timeArgsMapper;

    @Override
    public TimeArgs selectOneByLastedTime() {
        return timeArgsMapper.SelectOneByLastedTime();
    }

    @Override
    public List<TimeArgs> selectAll() {
        return timeArgsMapper.selectAll();
    }

    @Override
    public int insert(TimeArgs timeArgs) {
        return timeArgsMapper.insert(timeArgs);
    }
}
