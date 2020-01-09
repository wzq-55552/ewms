package com.soft.one.ewms.business.permission.service.impl;

import com.soft.one.ewms.domain.OperationRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.OperationRoleMapper;
import com.soft.one.ewms.business.permission.service.OperationRoleService;

import java.util.List;

@Service
public class OperationRoleServiceImpl implements OperationRoleService{

    @Resource
    private OperationRoleMapper operationRoleMapper;

    @Override
    public List<OperationRole> selectAll() {
        return operationRoleMapper.selectAll();
    }

    @Override
    public OperationRole selectById(OperationRole operationRole) {
        return operationRoleMapper.selectByPrimaryKey(operationRole);
    }

    @Override
    public void insert(OperationRole operationRole) {
        operationRoleMapper.insert(operationRole);
    }

    @Override
    public void update(OperationRole operationRole) {
        operationRoleMapper.updateByPrimaryKey(operationRole);
    }

    @Override
    public void delete(OperationRole operationRole) {
        operationRoleMapper.delete(operationRole);
    }
}
