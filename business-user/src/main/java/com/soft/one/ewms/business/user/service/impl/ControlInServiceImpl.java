package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.ControlInMapper;
import com.soft.one.ewms.business.user.service.ControlInService;
@Service
public class ControlInServiceImpl implements ControlInService{

    @Resource
    private ControlInMapper controlInMapper;

}
