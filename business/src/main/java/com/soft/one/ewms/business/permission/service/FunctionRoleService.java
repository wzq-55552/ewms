package com.soft.one.ewms.business.permission.service;

import com.soft.one.ewms.domain.FunctionRole;

import java.util.List;

public interface FunctionRoleService{

    List<FunctionRole> selectAll();
    FunctionRole selectById(FunctionRole fr);
    void insert(FunctionRole functionRole);
    void update(FunctionRole functionRole);
    void delete(FunctionRole functionRole);

}
