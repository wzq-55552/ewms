package com.soft.one.ewms.business.account.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.account.mapper.ControlInMapper;
import com.soft.one.ewms.business.account.service.ControlInService;
@Service
public class ControlInServiceImpl implements ControlInService{

    @Resource
    private ControlInMapper controlInMapper;

}
