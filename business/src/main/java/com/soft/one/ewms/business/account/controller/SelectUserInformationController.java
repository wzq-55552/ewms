package com.soft.one.ewms.business.account.controller;


import com.soft.one.ewms.business.account.service.UserInformationService;
import com.soft.one.ewms.domain.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SelectUserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    /**
     * 查询个人资料
     * @param userid
     * @return
     */
    @RequestMapping(value = "selectuserinfo",method = RequestMethod.POST)
    public UserInformation selectuserinfo(String userid){
        return userInformationService.selectByPrimaryKey(userid);
    }


}
