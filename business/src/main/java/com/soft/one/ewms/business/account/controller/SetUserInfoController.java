package com.soft.one.ewms.business.account.controller;


import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SetUserInfoController {

    @Autowired
    private UserInformationService userInformationService;

    /**
     * 管理员根据userid设定用户密码和手机
     * @param userPsw
     * @param username
     * @param httpServletRequest
     */
    @RequestMapping(value = "setuserinfo",method = RequestMethod.POST)
    public void setuserinfo(String userPsw,String username,HttpServletRequest httpServletRequest){
        UserInformation userInformation = userInformationService.selectByPrimaryKey((String) httpServletRequest.getSession().getAttribute("userid"));
        userInformation.setUserPsw(userPsw);
        userInformation.setUserName(username);
        userInformationService.setuserinfo(userInformation);
    }

}
