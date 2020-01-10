package com.soft.one.ewms.business.account.service.impl;

import com.soft.one.ewms.domain.ControlIn;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.account.mapper.ControlInMapper;
import com.soft.one.ewms.business.account.service.ControlInService;

import java.util.List;

@Service
public class ControlInServiceImpl implements ControlInService{

    @Resource
    private ControlInMapper controlInMapper;


    @Override
    public void insertuserid(ControlIn controlln) {
         controlInMapper.insert(controlln);
    }

    @Override
    public List<ControlIn> selectAll() {
        return controlInMapper.selectAll();
    }


}
