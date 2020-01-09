package com.soft.one.ewms.business.account.controller;

import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

public class EditUserInformationController {

    @Autowired
    UserInformationService userInformationService;

    @RequestMapping(value = "editinformation",method = RequestMethod.GET)
    public String editinformation()
    {
        return "editinformation";
    }

    @RequestMapping(value = "editinformation",method = RequestMethod.POST)
    public String editinformation(String username, String tel,String userPsw, HttpServletRequest httpServletRequest){
        UserInformation userInformation = userInformationService.selectByPrimaryKey((String) httpServletRequest.getSession().getAttribute("userid"));
        userInformation.setUserName(username);
        userInformation.setUserPsw(userPsw);
        userInformation.setTel(tel);
        userInformationService.updateinformation(userInformation);
        return "redirect:/login";
    }



}
