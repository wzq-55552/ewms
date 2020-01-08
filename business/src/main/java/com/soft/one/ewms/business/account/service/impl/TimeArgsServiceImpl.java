package com.soft.one.ewms.business.account.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.account.mapper.TimeArgsMapper;
import com.soft.one.ewms.business.account.service.TimeArgsService;
@Service
public class TimeArgsServiceImpl implements TimeArgsService{

    @Resource
    private TimeArgsMapper timeArgsMapper;

}
