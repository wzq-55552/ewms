package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.ControlIn;

public interface ControlInService {

    ControlIn selectByPrimaryKey(String id);

    int insert(ControlIn controlIn);

    int delete(ControlIn controlIn);

}
