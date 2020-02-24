package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.FunctionRole;

import java.util.List;

public interface FunctionRoleService{

    List<FunctionRole> selectAll();

    FunctionRole selectByPrimaryKey(String frId);

    FunctionRole selectOne(FunctionRole functionRole);

    int insert(FunctionRole functionRole);

    int updateByPrimaryKeySelective(FunctionRole functionRole);

    int deleteByPrimaryKey(String frId);

    /**
     * 组合模糊搜索
     * @param functionRole
     * @return
     */
    List<FunctionRole> selectByAll(FunctionRole functionRole);

}
