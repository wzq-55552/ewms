package com.soft.one.ewms.business.permission.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.FunctionRoleMapper;
import com.soft.one.ewms.business.permission.service.FunctionRoleService;
@Service
public class FunctionRoleServiceImpl implements FunctionRoleService{

    @Resource
    private FunctionRoleMapper functionRoleMapper;

}
