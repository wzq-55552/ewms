package com.soft.one.ewms.business.account.controller;


import com.soft.one.ewms.business.account.service.TimeArgsService;
import com.soft.one.ewms.domain.TimeArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class SetTimeArgController {

    @Autowired
    private TimeArgsService timeArgsService;

    /**
     * 设定时间参数
     * @param stayTime
     * @param remindTime
     * @param httpServletRequest
     */
    @RequestMapping(value = "settimearg",method = RequestMethod.POST)
    public void settimearg(Integer stayTime, Integer remindTime, HttpServletRequest httpServletRequest){
        TimeArgs timeArgs = new TimeArgs();
        timeArgs.setUser_id((String) httpServletRequest.getSession().getAttribute("userid"));
        timeArgs.setArgs_date(new Date());
        timeArgs.setStay_time(stayTime);
        timeArgs.setRemind_time(remindTime);
        timeArgsService.updateTimeArgs(timeArgs);
    }

    /**
     * 查询时间参数档所有信息
     * @return
     */
    @RequestMapping(value = "showtimeargs",method = RequestMethod.GET)
    public TimeArgs showtimeargs(){
        return timeArgsService.selectTimeArgs();
    }

}
