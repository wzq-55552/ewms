package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.OperationRange;

import java.util.List;

public interface OperationRangeService{

    /**
     * 为该操作资料角色增加可访问的仓库，建立联系集合
     * @param roidId
     * @param accNos
     */
    void insertList(String roidId, List<String> accNos);

    OperationRange select(OperationRange operationRange);

    int deleteByRoidId(String roidId);

    /**
     * 返回可以访问的仓库账号acc_no
     * @param roidId
     * @return
     */
    List<String> selectByRoidId(String roidId);
}
