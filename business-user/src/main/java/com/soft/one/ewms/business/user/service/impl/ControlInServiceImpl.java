package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.ControlIn;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.ControlInMapper;
import com.soft.one.ewms.business.user.service.ControlInService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ControlInServiceImpl implements ControlInService{

    @Resource
    private ControlInMapper controlInMapper;

    @Override
    public List<ControlIn> selectByUserId(String userId) {
        Example example = new Example(ControlIn.class);
        example.createCriteria().andEqualTo("userId",userId);
        return controlInMapper.selectByExample(example);
    }

    @Override
    public int insert(ControlIn controlIn) {
        return controlInMapper.insert(controlIn);
    }

    @Override
    public int delete(ControlIn controlIn) {
        return controlInMapper.delete(controlIn);
    }
}
