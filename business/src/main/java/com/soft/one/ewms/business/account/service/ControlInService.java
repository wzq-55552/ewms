package com.soft.one.ewms.business.account.service;

import com.soft.one.ewms.domain.ControlIn;

import java.util.List;

public interface ControlInService{

    void insertuserid(ControlIn controlIn);
    List<ControlIn> selectAll();

}
