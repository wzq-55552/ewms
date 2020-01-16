package com.soft.one.ewms.business.account.controller;


import com.soft.one.ewms.business.account.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping(value = "setinfo")
public class SetUserInfoController {

    @Autowired
    private UserInformationService userInformationService;

    /**
     * 管理员根据userid设定用户密码和手机
     * @param userid
     * @param userPsw
     * @param username
     */
    @RequestMapping(value = "setuserinfo",method = RequestMethod.POST)
    public void setuserinfo(String userid,String userPsw,String username){
        userInformationService.setuserinfo(userid,username,userPsw);
    }

}
