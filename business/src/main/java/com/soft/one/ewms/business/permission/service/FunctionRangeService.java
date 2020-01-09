package com.soft.one.ewms.business.permission.service;

import com.soft.one.ewms.domain.FunctionRange;

import java.util.List;

public interface FunctionRangeService{

    List<String> selectByFrId(FunctionRange f);
}
