package com.soft.one.ewms.domain.mappers.user;

import com.soft.one.ewms.domain.dtos.user.UserRoleDto;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface UserInformationMapper extends MyMapper<UserInformation> {

    /**
     * 组合模糊搜索
     * @param userRoleDto
     * @return
     */
    List<UserInformation> selectByDto(@Param("userRoleDto") UserRoleDto userRoleDto);

}