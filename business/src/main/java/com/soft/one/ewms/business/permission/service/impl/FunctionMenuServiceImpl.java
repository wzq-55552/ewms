package com.soft.one.ewms.business.permission.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.FunctionMenuMapper;
import com.soft.one.ewms.business.permission.service.FunctionMenuService;
@Service
public class FunctionMenuServiceImpl implements FunctionMenuService{

    @Resource
    private FunctionMenuMapper functionMenuMapper;

}
