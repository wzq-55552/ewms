package com.soft.one.ewms.business.user.test;

import com.soft.one.ewms.business.user.service.LogInService;

import com.soft.one.ewms.domain.pojos.user.LogIn;
import io.searchbox.client.JestClient;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class LoginSearchTest {

    @Autowired
    private JestClient jestClient;

    @Resource
    private LogInService logInService;

    // 添加es索引数据
    @Test
    public void testSave(){

    }

    // tk组合查询，不是模糊
    @Test
    public void testSearch(){
        LogIn logIn = new LogIn();
        logIn.setUserId("1");
        logIn.setEquipment("123456");
        List<LogIn> logIns = logInService.select(logIn);
        System.out.println(logIns.size());
        logIns.forEach(logIn1 -> {
            System.out.println(logIn1.getUserId());
            System.out.println(logIn1.getEquipment());
            System.out.println(logIn1.getInDate().toString());
        });
    }

    // 测试模糊查询
    // 起始可以，结束时间相差8小时
    @Test
    public void testSelectDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date beginDate = simpleDateFormat.parse("2020-02-21 11:30:01");
        Date endDate = simpleDateFormat.parse("2020-02-21 18:00:00");
        // 结束时间加8小时
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.HOUR, 8); // 24小时制
        // 更新后的时间
        endDate = cal.getTime();
        String userId = null;
        //String userId = "";
        // String userId = "1";
        String eq = "123";
        List<LogIn> logIns = logInService.selectByAll(userId, eq, beginDate, endDate);
        logIns.forEach(logIn -> {
            System.out.println(logIn.getFirstId());
            System.out.println(logIn.getUserId());
            System.out.println(logIn.getEquipment());
        });
    }
}
