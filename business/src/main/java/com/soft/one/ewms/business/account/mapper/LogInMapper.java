package com.soft.one.ewms.business.account.mapper;

import com.soft.one.ewms.domain.LogIn;
import tk.mybatis.mapper.TkMybatisMapper;

public interface LogInMapper extends TkMybatisMapper<LogIn> {

    void updateloginfo(LogIn login);

}