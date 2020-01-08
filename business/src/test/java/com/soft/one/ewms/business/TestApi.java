package com.soft.one.ewms.business;


import com.soft.one.ewms.commons.utils.MapperUtils;
import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import com.soft.one.ewms.domain.LmsWarehouseAcc;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApi {

    /**
     * 测试获取和解析json
     */
    @Test
    public void testapi(){
        //获取json,先启动api模块，不然没有数据
        Response response  = OkHttpClientUtil.getInstance().getData("http://localhost:8081/api/acc/all");

        try{
            //解析json
            String jsonString = response.body().string();//json格式数据
            List<LmsWarehouseAcc> jsonList = MapperUtils.json2listByTree(jsonString,"data",LmsWarehouseAcc.class);;//解析成map
            for (LmsWarehouseAcc lmsWarehouseAcc : jsonList
                 ) {
                System.out.println(lmsWarehouseAcc.getAcc_no());
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
