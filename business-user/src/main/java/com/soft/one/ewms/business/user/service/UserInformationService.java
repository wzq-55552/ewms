package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.dtos.user.UserRoleDto;
import com.soft.one.ewms.domain.pojos.user.UserInformation;

import java.util.List;

public interface UserInformationService{

    int updateByPrimaryKeySelective(UserInformation userInformation);

    int insert(UserInformation userInformation);

    UserInformation selectByPrimaryKey(String userId);

    int deleteByPrimaryKey(String userId);

    List<UserInformation> selectAll();

    /**
     * 组合模糊搜索
     * @param userRoleDto
     * @return
     */
    List<UserInformation> selectByDto(UserRoleDto userRoleDto);
}
