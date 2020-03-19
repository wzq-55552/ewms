package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.OperationRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.OperationRoleMapper;
import com.soft.one.ewms.business.user.service.OperationRoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OperationRoleServiceImpl implements OperationRoleService{

    @Resource
    private OperationRoleMapper operationRoleMapper;

    @Override
    public List<OperationRole> selectAll() {
        return operationRoleMapper.selectAll();
    }

    @Override
    public OperationRole selectByPrimaryKey(String roidId) {
        return operationRoleMapper.selectByPrimaryKey(roidId);
    }

    @Override
    public OperationRole selectOne(OperationRole operationRole) {
        return operationRoleMapper.selectOne(operationRole);
    }

    @Override
    public int insert(OperationRole operationRole) {
        return operationRoleMapper.insert(operationRole);
    }

    @Override
    public int updateByPrimaryKeySelective(OperationRole operationRole) {
        return operationRoleMapper.updateByPrimaryKeySelective(operationRole);
    }

    @Override
    public int deleteByPrimary(String roidId) {
        return operationRoleMapper.deleteByPrimaryKey(roidId);
    }

    @Override
    public List<OperationRole> selectByAll(String roidId, String roidType) {
        return operationRoleMapper.selectByAll(roidId, roidType);
    }

}
