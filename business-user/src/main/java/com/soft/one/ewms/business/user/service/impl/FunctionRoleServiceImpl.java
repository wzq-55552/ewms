package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.FunctionRoleMapper;
import com.soft.one.ewms.business.user.service.FunctionRoleService;
@Service
public class FunctionRoleServiceImpl implements FunctionRoleService{

    @Resource
    private FunctionRoleMapper functionRoleMapper;

}
