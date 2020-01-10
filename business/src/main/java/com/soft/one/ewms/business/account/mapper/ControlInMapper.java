package com.soft.one.ewms.business.account.mapper;

import com.soft.one.ewms.domain.ControlIn;
import tk.mybatis.mapper.TkMybatisMapper;

public interface ControlInMapper extends TkMybatisMapper<ControlIn> {

    Long selectserialVersionUID(String userid);

}