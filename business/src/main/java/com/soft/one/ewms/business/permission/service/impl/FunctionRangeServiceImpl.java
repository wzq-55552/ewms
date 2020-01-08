package com.soft.one.ewms.business.permission.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.FunctionRangeMapper;
import com.soft.one.ewms.business.permission.service.FunctionRangeService;
@Service
public class FunctionRangeServiceImpl implements FunctionRangeService{

    @Resource
    private FunctionRangeMapper functionRangeMapper;

}
