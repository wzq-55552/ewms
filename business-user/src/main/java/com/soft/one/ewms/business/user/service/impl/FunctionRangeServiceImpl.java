package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import com.soft.one.ewms.domain.pojos.user.OperationRange;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.FunctionRangeMapper;
import com.soft.one.ewms.business.user.service.FunctionRangeService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class FunctionRangeServiceImpl implements FunctionRangeService{

    @Resource
    private FunctionRangeMapper functionRangeMapper;

    @Override
    public List<FunctionRange> selectByFrId(String frId) {
        Example example = new Example(FunctionRange.class);
        example.createCriteria().andEqualTo("frId",frId);
        return functionRangeMapper.selectByExample(example);
    }

    @Override
    public void insertList(String frId, List<String> fIds) {
        if (fIds.size() > 0){
            // 这里不去重了，前端页面是打勾的，不会重复
            for (String fId : fIds) {
                FunctionRange functionRange = new FunctionRange();
                functionRange.setFrId(frId);
                functionRange.setFId(fId);
                functionRangeMapper.insert(functionRange);
            }
        }
    }

    @Override
    public int deleteByFrId(String frId) {
        Example example = new Example(FunctionRange.class);
        example.createCriteria().andEqualTo("frId", frId);
        return functionRangeMapper.deleteByExample(example);
    }
}
