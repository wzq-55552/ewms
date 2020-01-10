package com.soft.one.ewms.business.permission.service;

import com.soft.one.ewms.domain.OperationRange;
import com.soft.one.ewms.domain.OperationRole;

import java.util.List;

public interface OperationRangeService{

    List<String>selectByRoleId(OperationRange operationRange);
    void insert(OperationRange operationRange);
    void update(OperationRange operationRange);
    void delete(OperationRange operationRange);

    OperationRange selectById(OperationRange operationRange);
}
