package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.ControlIn;

import java.util.List;

public interface ControlInService{

    List<ControlIn> selectByUserId(String userId);

    int insert(ControlIn controlIn);

    int delete(ControlIn controlIn);

}
