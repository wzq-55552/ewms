package com.soft.one.ewms.domain.mappers.user;

import com.soft.one.ewms.domain.pojos.user.LogIn;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;

public interface LogInMapper extends MyMapper<LogIn> {


    /**
     * 3个条件模糊查询，含有时间段
     * @param beginDate
     * @param endDate
     * @return
     */
    List<LogIn> selectByAll(@Param("userId")String userId,@Param("equipment")String equipment, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    /**
     * 查询该用户OutDate为空的数据
     * @param userId
     * @return
     */
    LogIn selectByUserIdAndOutDate(String userId);
}