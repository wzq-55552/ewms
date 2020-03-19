package com.soft.one.ewms.security.oauth2.service.impl;

import com.soft.one.ewms.domain.mappers.user.ControlInMapper;
import com.soft.one.ewms.domain.pojos.user.ControlIn;
import com.soft.one.ewms.security.oauth2.service.ControlInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ControlInServiceImpl implements ControlInService {

    @Resource
    private ControlInMapper controlInMapper;

    @Override
    public ControlIn selectByPrimaryKey(String id) {
        return controlInMapper.selectByPrimaryKey(id);
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
