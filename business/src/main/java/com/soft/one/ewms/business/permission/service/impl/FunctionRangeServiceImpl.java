package com.soft.one.ewms.business.permission.service.impl;

import com.soft.one.ewms.domain.FunctionRange;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.FunctionRangeMapper;
import com.soft.one.ewms.business.permission.service.FunctionRangeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionRangeServiceImpl implements FunctionRangeService{

    @Resource
    private FunctionRangeMapper functionRangeMapper;


    /**
     * 根据FunctionRange对象实例查询（所有条件同时成立）
     * @param f
     * @return
     */
    public List<String> selectByFrId(FunctionRange f){
        List<String>ids = new ArrayList<>();
        List<FunctionRange> functionRanges = functionRangeMapper.select(f);
        for (FunctionRange functionRange : functionRanges) {
            ids.add(functionRange.getFId());
        }
        return ids;
    }
}
