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
     * @param functionRange
     * @return List(f_id)
     */
    public List<String> selectFIdByExample(FunctionRange functionRange) {
        List<String>ids = new ArrayList<>();
        List<FunctionRange> functionRanges = functionRangeMapper.select(functionRange);
        for (FunctionRange f : functionRanges) {
            ids.add(f.getFId());
        }
        return ids;
    }

    /**
     * 根据FunctionRange对象实例查询（所有条件同时成立）
     *
     * @param functionRange
     * @return List(fr_id)
     */
    public List<String> selectFrIdByExample(FunctionRange functionRange) {
        List<String> ids = new ArrayList<>();
        List<FunctionRange> functionRanges = functionRangeMapper.select(functionRange);
        for (FunctionRange f : functionRanges) {
            ids.add(f.getFrId());
        }
        return ids;
    }


    @Override
    public FunctionRange selectById(FunctionRange functionRange) {
        return functionRangeMapper.selectByPrimaryKey(functionRange);
    }

    @Override
    public void insert(FunctionRange functionRange) {
        functionRangeMapper.insert(functionRange);
    }

    @Override
    public void delete(FunctionRange functionRange) {
        functionRangeMapper.deleteByPrimaryKey(functionRange);
    }

    @Override
    public void update(FunctionRange functionRange) {
        functionRangeMapper.updateByPrimaryKey(functionRange);
    }
}
