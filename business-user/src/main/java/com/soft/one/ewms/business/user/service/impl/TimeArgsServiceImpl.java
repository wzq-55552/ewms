package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.TimeArgsMapper;
import com.soft.one.ewms.business.user.service.TimeArgsService;
@Service
public class TimeArgsServiceImpl implements TimeArgsService{

    @Resource
    private TimeArgsMapper timeArgsMapper;

}
