package com.soft.one.ewms.business.account.controller;

import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *@ClassName:EditUserInformation
 *@Author:Administrator
 *@Date:2020/1/8 20:16
 *@Version 1.0
 */
@Controller
@RequestMapping(value = "editinfo")
public class EditUserInformationController {

    @Autowired
    UserInformationService userInformationService;

//    @RequestMapping(value = "editinformation",method = RequestMethod.GET)
//    public String editinformation()
//    {
//        return "editinformation";
//    }

    @RequestMapping(value = "editinformation",method = RequestMethod.POST)
    public String editinformation(String userid,String username,String userPsw,String Tel, HttpServletRequest httpServletRequest){
        UserInformation userInformation = userInformationService.selectByUserid(userid);
        System.out.println(userInformation);
        userInformation.setTel(Tel);
        userInformation.setUser_name(username);
        userInformation.setUser_psw(userPsw);
        userInformationService.updateinformation(userInformation);
        return "redirect:/main";
    }




}
