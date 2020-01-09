package com.soft.one.ewms.business.permission.service;

import com.soft.one.ewms.domain.FunctionMenu;

import java.util.List;

public interface FunctionMenuService{

    void insert(FunctionMenu functionMenu);
    FunctionMenu selectById(FunctionMenu functionMenu);
    List<FunctionMenu> selectAll();
    void update(FunctionMenu functionMenu);
    void delete(FunctionMenu functionMenu);
}
