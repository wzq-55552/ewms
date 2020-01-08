package com.soft.one.ewms.business.permission.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.OperationRoleMapper;
import com.soft.one.ewms.business.permission.service.OperationRoleService;
@Service
public class OperationRoleServiceImpl implements OperationRoleService{

    @Resource
    private OperationRoleMapper operationRoleMapper;

}
