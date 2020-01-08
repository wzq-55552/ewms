package com.soft.one.ewms.business.account.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.account.mapper.UserInformationMapper;
import com.soft.one.ewms.business.account.service.UserInformationService;
@Service
public class UserInformationServiceImpl implements UserInformationService{

    @Resource
    private UserInformationMapper userInformationMapper;

}
