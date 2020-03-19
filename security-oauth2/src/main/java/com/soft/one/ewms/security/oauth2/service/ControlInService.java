package com.soft.one.ewms.security.oauth2.service;

import com.soft.one.ewms.domain.pojos.user.ControlIn;

import java.util.List;

public interface ControlInService {

    ControlIn selectByPrimaryKey(String id);

    int insert(ControlIn controlIn);

    int delete(ControlIn controlIn);

}
