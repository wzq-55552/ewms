package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.OperationRangeMapper;
import com.soft.one.ewms.business.user.service.OperationRangeService;
@Service
public class OperationRangeServiceImpl implements OperationRangeService{

    @Resource
    private OperationRangeMapper operationRangeMapper;

}
