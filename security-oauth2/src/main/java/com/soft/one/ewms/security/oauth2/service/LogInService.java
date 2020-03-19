package com.soft.one.ewms.security.oauth2.service;

import com.soft.one.ewms.domain.pojos.user.LogIn;

import java.util.Date;
import java.util.List;

public interface LogInService {

    int insert(LogIn logIn);

    /**
     * 查询该用户OutDate为空的数据
     * @param userId
     * @return
     */
    LogIn selectByUserIdAndOutDate(String userId);

    int updateByPrimaryKeySelective(LogIn logIn);
}
