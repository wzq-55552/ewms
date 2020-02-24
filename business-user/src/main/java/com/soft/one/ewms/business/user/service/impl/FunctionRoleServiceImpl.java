package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.FunctionRoleMapper;
import com.soft.one.ewms.business.user.service.FunctionRoleService;

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
    public FunctionRole selectByPrimaryKey(String frId) {
        return functionRoleMapper.selectByPrimaryKey(frId);
    }

    @Override
    public FunctionRole selectOne(FunctionRole functionRole) {
        return functionRoleMapper.selectOne(functionRole);
    }

    @Override
    public int insert(FunctionRole functionRole) {
        return functionRoleMapper.insert(functionRole);
    }

    @Override
    public int updateByPrimaryKeySelective(FunctionRole functionRole) {
        return functionRoleMapper.updateByPrimaryKeySelective(functionRole);
    }

    @Override
    public int deleteByPrimaryKey(String frId) {
        return functionRoleMapper.deleteByPrimaryKey(frId);
    }

    @Override
    public List<FunctionRole> selectByAll(FunctionRole functionRole) {
        return functionRoleMapper.selectByAll(functionRole);
    }
}
