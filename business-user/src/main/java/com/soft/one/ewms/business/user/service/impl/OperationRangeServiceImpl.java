package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.OperationRange;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.OperationRangeMapper;
import com.soft.one.ewms.business.user.service.OperationRangeService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OperationRangeServiceImpl implements OperationRangeService{

    @Resource
    private OperationRangeMapper operationRangeMapper;

    @Override
    public void insertList(String roidId, List<String> accNos) {
        if (accNos.size() > 0){
            // 这里不去重了，前端页面是打勾的，不会重复
            for (String accNo : accNos) {
                OperationRange operationRange = new OperationRange();
                operationRange.setAccNo(accNo);
                operationRange.setRoidId(roidId);
                operationRangeMapper.insert(operationRange);
            }
        }
    }

    @Override
    public OperationRange select(OperationRange operationRange) {
        return operationRangeMapper.selectOne(operationRange);
    }

    @Override
    public int deleteByRoidId(String roidId) {
        Example example = new Example(OperationRange.class);
        example.createCriteria().andEqualTo("roidId", roidId);
        return operationRangeMapper.deleteByExample(example);
    }

    @Override
    public List<String> selectByRoidId(String roidId) {
        Example example = new Example(OperationRange.class);
        example.createCriteria().andEqualTo("roidId", roidId);
        List<OperationRange> operationRanges = operationRangeMapper.selectByExample(example);
        List<String> strings = new ArrayList<>();
        operationRanges.forEach(operationRange -> {
            strings.add(operationRange.getAccNo());
        });
        return strings;
    }
}
