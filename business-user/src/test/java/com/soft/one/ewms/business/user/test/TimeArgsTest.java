package com.soft.one.ewms.business.user.test;

import com.soft.one.ewms.business.user.service.TimeArgsService;
import com.soft.one.ewms.domain.pojos.user.TimeArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeArgsTest {

    @Resource
    private TimeArgsService timeArgsService;

    @Test
    public void testLastedTime(){
        TimeArgs args = timeArgsService.selectOneByLastedTime();
        System.out.println(args.getRemindTime());
        System.out.println(args.getStayTime());
    }

}
