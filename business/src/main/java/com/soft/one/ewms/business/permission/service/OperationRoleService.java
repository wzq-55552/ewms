package com.soft.one.ewms.business.permission.service;

import com.soft.one.ewms.domain.OperationRole;

import java.util.List;

public interface OperationRoleService{

    List<OperationRole>selectAll();
    OperationRole selectById(OperationRole operationRole);
    void insert(OperationRole operationRole);
    void update(OperationRole operationRole);
    void delete(OperationRole operationRole);
}
