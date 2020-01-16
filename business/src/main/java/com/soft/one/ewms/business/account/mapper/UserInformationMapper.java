package com.soft.one.ewms.business.account.mapper;

import com.soft.one.ewms.domain.UserInformation;
import tk.mybatis.mapper.TkMybatisMapper;

public interface UserInformationMapper extends TkMybatisMapper<UserInformation> {

    void updateinformation(UserInformation userInformation);
    void setuserinfo(String userid,String username,String userPsw);
    UserInformation selectByUserid(String userid);
}