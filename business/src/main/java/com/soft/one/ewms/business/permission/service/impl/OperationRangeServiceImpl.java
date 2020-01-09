package com.soft.one.ewms.business.permission.service.impl;

import com.soft.one.ewms.domain.OperationRange;
import com.soft.one.ewms.domain.OperationRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.OperationRangeMapper;
import com.soft.one.ewms.business.permission.service.OperationRangeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationRangeServiceImpl implements OperationRangeService{

    @Resource
    private OperationRangeMapper operationRangeMapper;

    @Override
    public List<String> selectByRoleId(OperationRange operationRange) {
        List<String> accNos = new ArrayList<>();
        List<OperationRange> operationRanges = operationRangeMapper.select(operationRange);
        for (OperationRange range : operationRanges) {
            accNos.add(range.getAccNo());
        }
        return accNos;
    }

    @Override
    public void insert(OperationRange operationRange) {
        operationRangeMapper.insert(operationRange);
    }

    @Override
    public void update(OperationRange operationRange) {
        operationRangeMapper.updateByPrimaryKey(operationRange);
    }

    @Override
    public void delete(OperationRange operationRange) {
        operationRangeMapper.deleteByPrimaryKey(operationRange);
    }
}
