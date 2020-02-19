package com.soft.one.ewms.security.oauth2.service.impl;

import com.soft.one.ewms.domain.mappers.user.UserInformationMapper;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import com.soft.one.ewms.security.oauth2.service.UserInformationService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    public UserInformation getByUserId(String s) {
        return userInformationMapper.selectByPrimaryKey(s);
    }
}
