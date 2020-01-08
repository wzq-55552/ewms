package com.soft.one.ewms.business.permission.controller;

import com.soft.one.ewms.commons.utils.MapperUtils;
import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import com.soft.one.ewms.domain.LmsWarehouseAcc;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

/**
 * Title：权限controller
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Controller
public class PermissionController {

    /**
     * 获取json数据并解析
     * @return
     */
    @GetMapping("/")
    public void test(){
        //获取json,先启动api模块，不然没有数据
        Response response  = OkHttpClientUtil.getInstance().getData("http://localhost:8082/api/acc/all");

        try{
            //解析json
            String jsonString = response.body().string();//json格式数据
            List<LmsWarehouseAcc> jsonList = MapperUtils.json2listByTree(jsonString,"data",LmsWarehouseAcc.class);;//找到“data”解析解析成List
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
