package com.soft.one.ewms.business.permission.service;

import com.soft.one.ewms.domain.FunctionRange;

import java.util.List;

public interface FunctionRangeService{

    List<String> selectFIdByExample(FunctionRange functionRange);

    List<String> selectFrIdByExample(FunctionRange functionRange);

    FunctionRange selectById(FunctionRange functionRange);

    void insert(FunctionRange functionRange);

    void delete(FunctionRange functionRange);

    void update(FunctionRange functionRange);
}
