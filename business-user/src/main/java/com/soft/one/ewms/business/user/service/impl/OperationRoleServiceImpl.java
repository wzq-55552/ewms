package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.OperationRoleMapper;
import com.soft.one.ewms.business.user.service.OperationRoleService;
@Service
public class OperationRoleServiceImpl implements OperationRoleService{

    @Resource
    private OperationRoleMapper operationRoleMapper;

}
