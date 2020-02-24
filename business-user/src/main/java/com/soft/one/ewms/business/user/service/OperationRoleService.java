package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.OperationRole;

import java.util.List;

public interface OperationRoleService{

    List<OperationRole> selectAll();

    OperationRole selectByPrimaryKey(String roidId);

    OperationRole selectOne(OperationRole operationRole);

    int insert(OperationRole operationRole);

    int updateByPrimaryKeySelective(OperationRole operationRole);

    int deleteByPrimary(String roidId);

    /**
     * 组合模糊搜索
     * @param roidId
     * @param roidType
     * @return
     */
    List<OperationRole> selectByAll(String roidId, String roidType);

}
