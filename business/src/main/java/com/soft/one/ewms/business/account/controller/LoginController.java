package com.soft.one.ewms.business.account.controller;

import com.soft.one.ewms.business.account.config.GetMacAddress;
import com.soft.one.ewms.business.account.service.ControlInService;
import com.soft.one.ewms.business.account.service.LogInService;
import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.ControlIn;
import com.soft.one.ewms.domain.LogIn;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class LoginController {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private ControlInService controlInService;

    @Autowired
    private LogInService logInService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String Login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String Login(String userId, String userPsw, Model model, HttpServletRequest httpServletRequest){
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
            httpServletRequest.getSession().setAttribute("userid",userInformation.getUserId());
            httpServletRequest.getSession().setAttribute("equipment",userInformation.getUserPsw());
            httpServletRequest.getSession().setAttribute("username",userInformation.getUserName());
            httpServletRequest.getSession().setAttribute("telephone",userInformation.getTel());
            httpServletRequest.getSession().setAttribute("frid",userInformation.getFrId());
            httpServletRequest.getSession().setAttribute("roidld",userInformation.getRoidId());
            httpServletRequest.getSession().setAttribute("pswdate",userInformation.getPswDate());
            controlIn.setUserId("userid");
            controlInService.insertuserid(controlIn);

            login.setUserId("userid");
            login.setEquipment(GetMacAddress.getOnly());
            login.setInDate(new Date());
            logInService.insertlogininformation(login);

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

}
