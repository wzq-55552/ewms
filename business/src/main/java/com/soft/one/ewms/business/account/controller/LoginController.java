package com.soft.one.ewms.business.account.controller;

import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:LoginController
 * @Author:Administrator
 * @Date:2020/1/815:15
 * @Version 1.0
 */

@RestController
public class LoginController {

    @Autowired
    UserInformationService userInformationService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String Login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String Login(String userId, String userPsw, Model model, HttpServletRequest httpServletRequest){
        UserInformation userInformation = userInformationService.login(userId,userPsw);
        //登陆失败
        if(userInformation == null)
        {
            model.addAttribute("message","登录失败，请输入正确的账号密码。");
            return Login();
        }
        //登陆成功
        else{
            //将登陆信息放入会话
            httpServletRequest.getSession().setAttribute("user",userInformation);
            httpServletRequest.getSession().setAttribute("userid",userInformation.getUserId());
            httpServletRequest.getSession().setAttribute("equipment",userInformation.getUserPsw());
            httpServletRequest.getSession().setAttribute("username",userInformation.getUserName());
            httpServletRequest.getSession().setAttribute("telephone",userInformation.getTel());
            httpServletRequest.getSession().setAttribute("frid",userInformation.getFrId());
            httpServletRequest.getSession().setAttribute("roidld",userInformation.getRoidId());
            httpServletRequest.getSession().setAttribute("pswdate",userInformation.getPswDate());
            return "redirect:/main";
        }
    }
}
