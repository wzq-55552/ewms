package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.FunctionMenuMapper;
import com.soft.one.ewms.business.user.service.FunctionMenuService;
@Service
public class FunctionMenuServiceImpl implements FunctionMenuService{

    @Resource
    private FunctionMenuMapper functionMenuMapper;

}
