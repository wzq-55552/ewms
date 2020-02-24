package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;

import java.util.List;

public interface FunctionMenuService{

    List<FunctionMenu> selectAll();

    FunctionMenu selectByPrimaryKey(String fId);

    int insert(FunctionMenu functionMenu);

    int updateByPrimaryKeySelective(FunctionMenu functionMenu);

    int deleteByPrimaryKey(String fId);

    FunctionMenu selectOne(FunctionMenu functionMenu);

    /**
     * 组合模糊搜索
     * @param functionMenu
     * @return
     */
    List<FunctionMenu> selectByAll(FunctionMenu functionMenu);

    /**
     * 权限关系表获取权限信息
     * @param functionRanges
     * @return
     */
    List<FunctionMenu> selectByList(List<FunctionRange> functionRanges);

}
