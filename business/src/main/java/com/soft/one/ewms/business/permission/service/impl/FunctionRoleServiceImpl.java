package com.soft.one.ewms.business.permission.service.impl;

import com.soft.one.ewms.domain.FunctionRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.FunctionRoleMapper;
import com.soft.one.ewms.business.permission.service.FunctionRoleService;

import java.util.List;

@Service
public class FunctionRoleServiceImpl implements FunctionRoleService{

    @Resource
    private FunctionRoleMapper functionRoleMapper;

    @Override
    public List<FunctionRole> selectAll() {
        return functionRoleMapper.selectAll();
    }

    @Override
    public FunctionRole selectById(FunctionRole fr) {
        return functionRoleMapper.selectByPrimaryKey(fr);
    }

    @Override
    public void insert(FunctionRole functionRole) {
        functionRoleMapper.insert(functionRole);
    }

    @Override
    public void update(FunctionRole functionRole) {
        functionRoleMapper.updateByPrimaryKey(functionRole);
    }

    @Override
    public void delete(FunctionRole functionRole) {
        functionRoleMapper.deleteByPrimaryKey(functionRole);
    }
}
