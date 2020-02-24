package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.LogIn;

import java.util.Date;
import java.util.List;

public interface LogInService{

    int insert(LogIn logIn);

    LogIn selectOne(LogIn logIn);

    List<LogIn> selectAll();

    /**
     * tk组合条件查询，没有模糊
     * @param logIn
     * @return
     */
    List<LogIn> select(LogIn logIn);

    /**
     * 时间段、用户id、设备id3个条件查询
     * @param userId
     * @param equipment
     * @param beginDate
     * @param endDate
     * @return
     */
    List<LogIn> selectByAll(String userId, String equipment, Date beginDate, Date endDate);

    /**
     * 查询该用户OutDate为空的数据
     * @param userId
     * @return
     */
    List<LogIn> selectByUserIdAndOutDate(String userId);

    int updateByPrimaryKeySelective(LogIn logIn);
}
