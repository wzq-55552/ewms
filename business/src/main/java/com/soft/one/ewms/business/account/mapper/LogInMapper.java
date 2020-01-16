package com.soft.one.ewms.business.account.mapper;

import com.soft.one.ewms.domain.LogIn;
import com.soft.one.ewms.domain.UserInformation;
import tk.mybatis.mapper.TkMybatisMapper;

public interface LogInMapper extends TkMybatisMapper<LogIn> {

    void updateloginfo(LogIn login);
    void deleteLoginfo(String userid);
    LogIn selectByuserid(String userid);

}