package com.soft.one.ewms.business.account.controller;

import com.soft.one.ewms.business.account.service.LogInService;
import com.soft.one.ewms.domain.LogIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LogoutController {

    @Autowired
    private LogInService logInService;

    /**
     * 用户登出接口
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        LogIn login = logInService.selectByUserid((String) httpServletRequest.getSession().getAttribute("userid"));
        System.out.println(login);
        //删除登录控制档中的userid
        logInService.deleteLoginfo(login.getUser_id());
        //设置登出触发者和登出时间
        login.setOut_type(0);
        login.setOut_date(new Date());
        logInService.updateloginfo(login);
        /**
         * 清除会话中所存放的登录信息
         */
        httpServletRequest.getSession().invalidate();
        return "login";
    }


    /**
     * 系统自动登出
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "autologout",method = RequestMethod.GET)
    public String autologout(HttpServletRequest httpServletRequest){
        LogIn login = logInService.selectByUserid((String) httpServletRequest.getSession().getAttribute("userid"));
        System.out.println(login);
        //删除登录控制档中的userid
        logInService.deleteLoginfo(login.getUser_id());
        //设置登出触发者和登出时间
        login.setOut_type(1);
        login.setOut_date(new Date());
        logInService.updateloginfo(login);
        /**
         * 清除会话中所存放的登录信息
         */
        httpServletRequest.getSession().invalidate();
        return "login";
    }

}
