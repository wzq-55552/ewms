package com.soft.one.ewms.business.account.service;


import com.soft.one.ewms.domain.LogIn;



public interface LogInService{


    void insertloginformation(LogIn login);
    LogIn selectByUserid(String userid);
    void deleteLoginfo(String userid);
    void updateloginfo(LogIn login);
}
