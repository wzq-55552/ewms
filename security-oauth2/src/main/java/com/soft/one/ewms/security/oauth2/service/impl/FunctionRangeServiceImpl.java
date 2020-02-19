package com.soft.one.ewms.security.oauth2.service.impl;

import com.soft.one.ewms.domain.mappers.user.FunctionRangeMapper;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import com.soft.one.ewms.security.oauth2.service.FunctionRangeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FunctionRangeServiceImpl implements FunctionRangeService {

    @Resource
    private FunctionRangeMapper functionRangeMapper;

    @Override
    public List<FunctionRange> selectByFrId(String frId) {
        Example example = new Example(FunctionRange.class);
        example.createCriteria().andEqualTo("frId",frId);
        return functionRangeMapper.selectByExample(example);
    }
}
