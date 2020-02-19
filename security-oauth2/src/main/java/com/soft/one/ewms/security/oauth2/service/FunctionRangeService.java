package com.soft.one.ewms.security.oauth2.service;

import com.soft.one.ewms.domain.pojos.user.FunctionRange;

import java.util.List;

public interface FunctionRangeService{


    /**
     * 通过FrId查询权限集
     * @param frId 功能角色
     * @return
     */
    List<FunctionRange> selectByFrId(String frId);

}
