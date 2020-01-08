package com.soft.one.ewms.business.permission.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.OperationRangeMapper;
import com.soft.one.ewms.business.permission.service.OperationRangeService;
@Service
public class OperationRangeServiceImpl implements OperationRangeService{

    @Resource
    private OperationRangeMapper operationRangeMapper;

}
