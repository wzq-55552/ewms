package com.soft.one.ewms.business.account.controller;

import com.soft.one.ewms.business.account.config.GetMacAddress;
import com.soft.one.ewms.business.account.service.ControlInService;
import com.soft.one.ewms.business.account.service.LogInService;
import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.ControlIn;
import com.soft.one.ewms.domain.LogIn;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:LoginController
 * @Author:Administrator
 * @Date:2020/1/815:15
 * @Version 1.0
 */

@Controller
public class LoginController {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private ControlInService controlInService;

    @Autowired
    private LogInService logInService;

    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String Login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String Login(String userId, String userPsw, Model model, HttpServletRequest httpServletRequest){
        System.out.println(userId+" "+userPsw);
        UserInformation userInformation = userInformationService.login(userId,userPsw);
        ControlIn controlIn = new ControlIn();
        LogIn login = new LogIn();
        //登陆失败
        if(userInformation == null )
        {
            model.addAttribute("message","登录失败，请输入正确的账号密码。");
            return Login();
        }
        else if( JudgeLogin(userId) ){
            model.addAttribute("message","登录失败,此用户已登录。");
            return Login();
        }
        //登陆成功
        else{
            //将登陆信息放入会话
            httpServletRequest.getSession().setAttribute("user",userInformation);
            httpServletRequest.getSession().setAttribute("userid",userInformation.getUser_id());
            httpServletRequest.getSession().setAttribute("equipment",userInformation.getUser_psw());
            httpServletRequest.getSession().setAttribute("username",userInformation.getUser_name());
            httpServletRequest.getSession().setAttribute("telephone",userInformation.getTel());
            httpServletRequest.getSession().setAttribute("frid",userInformation.getFr_Id());
            httpServletRequest.getSession().setAttribute("roidld",userInformation.getRoid_Id());
            httpServletRequest.getSession().setAttribute("pswdate",userInformation.getPsw_date());
            controlIn.setUserId(userInformation.getUser_id());
            controlInService.insertuserid(controlIn);

            login.setUser_id((String) httpServletRequest.getSession().getAttribute("userid"));
            login.setEquipment(GetMacAddress.getOnly());
            login.setIn_date(new Date());
            logInService.insertloginformation(login);
//            System.out.println("----------------------------------------------");
            return "redirect:/main";
        }
    }

    /**
     * 判断此用户是否在登录
     * @param userid
     * @return
     */
    public boolean JudgeLogin(String userid){

        List<ControlIn> result = controlInService.selectAll();
        for (ControlIn controlIn : result) {
            if(userid.equals(controlIn.getUserId())){
                return true;
            }
        }
        return false;
    }

    /**
     * 反馈登录状态给前端
     * @param userid
     */
    @RequestMapping(value = "returnlogstatus",method = RequestMethod.POST)
    public void returnlogstatus(String userid){
        JudgeLogin(userid);
    }

    @RequestMapping(value ="main",method = RequestMethod.GET)
    public String Main(){
        return "Main";
    }


}
