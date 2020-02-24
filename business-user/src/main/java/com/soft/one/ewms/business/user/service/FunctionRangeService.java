package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.FunctionRange;

import java.util.List;

public interface FunctionRangeService{


    List<FunctionRange> selectByFrId(String frId);

    /**
     * 为该功能角色增加可访问的功能，建立联系集合
     * @param frId
     * @param fIds
     */
    void insertList(String frId, List<String> fIds);

    int deleteByFrId(String frId);

}
