package com.soft.one.ewms.business.user.test;

import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpTest {

    //http://www.taobao.com/help/getip.php
    @Test
    public void testIp() {
        Response response  = OkHttpClientUtil.getInstance().getData("http://www.taobao.com/help/getip.php");
        try{
            //解析json
            String jsonString = response.body().string();//json格式数据
            System.out.println(jsonString);

        }catch (Exception e) {

        }
    }

    //113.59.42.155

    @Test
    public void testIpAddress() {
        //http://ip.taobao.com/service/getIpInfo.php
        Map<String, String> ip = new HashMap<>();
        ip.put("ip","113.59.42.155");
        System.out.println(ip.toString());
        Response response  = OkHttpClientUtil.getInstance().postData("http://ip.taobao.com/service/getIpInfo.php",ip);
        try{
            //解析json
            String jsonString = response.body().string();//json格式数据
            System.out.println(jsonString);

        }catch (Exception e) {

        }
    }
}
