package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.dtos.user.UserRoleDto;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.UserInformationMapper;
import com.soft.one.ewms.business.user.service.UserInformationService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserInformationServiceImpl implements UserInformationService{

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    public int updateByPrimaryKeySelective(UserInformation userInformation) {
        return userInformationMapper.updateByPrimaryKeySelective(userInformation);
    }

    @Override
    public int insert(UserInformation userInformation) {
        return userInformationMapper.insert(userInformation);
    }

    @Override
    public UserInformation selectByPrimaryKey(String userId) {
        return userInformationMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int deleteByPrimaryKey(String userId) {
        return userInformationMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public List<UserInformation> selectAll() {
        return userInformationMapper.selectAll();
    }

    @Override
    public  List<UserInformation> selectByDto(UserRoleDto userRoleDto) {
        if (userRoleDto != null){
            return userInformationMapper.selectByDto(userRoleDto);
        }
        return null;
    }


}
