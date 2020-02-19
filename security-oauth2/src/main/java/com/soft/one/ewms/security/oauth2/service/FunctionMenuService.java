package com.soft.one.ewms.security.oauth2.service;

import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;

import java.util.List;

public interface FunctionMenuService{

    /**
     * 通过关联集合获取到对应的权限集合，可拿到权限url、权限名
     * @param functionRanges 关联集合
     * @return
     */
    List<FunctionMenu> selectByList(List<FunctionRange> functionRanges);

}
