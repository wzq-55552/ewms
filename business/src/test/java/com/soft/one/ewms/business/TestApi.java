package com.soft.one.ewms.business;


import com.soft.one.ewms.business.permission.service.FunctionMenuService;
import com.soft.one.ewms.business.permission.service.FunctionRangeService;
import com.soft.one.ewms.business.permission.service.FunctionRoleService;
import com.soft.one.ewms.commons.utils.MapperUtils;
import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import com.soft.one.ewms.domain.FunctionMenu;
import com.soft.one.ewms.domain.FunctionRole;
import com.soft.one.ewms.domain.LmsWarehouseAcc;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApi {

    private static Logger logger = LoggerFactory.getLogger(TestApi.class);

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

    @Autowired
    private FunctionMenuService fms;

    @Autowired
    private  FunctionRangeService frs;

    @Autowired
    private FunctionRoleService froleS;

    /**
     * 测试FunctionMenu
     */
    @Test
    public void testFunctionMenu() {
        List<FunctionMenu> functionMenus = fms.selectAll();
        for (FunctionMenu functionMenu : functionMenus) {
            System.out.println(functionMenu.getFId() + "..." + functionMenu.getFName());
        }
        FunctionMenu functionMenu = new FunctionMenu();
        functionMenu.setFId("");
        functionMenu.setFName("sad");
        fms.insert(functionMenu);
    }


    /**
     * 测试获取当前设备IP
     */
    @Test
    public void testmac(){

    }

    /**
     * 测试FunctionRole
     */
    @Test
    public void testFunctionRole(){
//        List<FunctionRole> functionRoles = froleS.selectAll();
//        for (FunctionRole functionRole : functionRoles) {
//            System.out.println(functionRole.getFrId()+"..."+functionRole.getFrName());
//        }

        FunctionRole functionRole = new FunctionRole();
        functionRole.setFrId("1");
        functionRole = froleS.selectById(functionRole);
        System.out.println(functionRole.getFrId()+"..."+functionRole.getFrName());
    }
    @Test
    public void testAddFunctionRole(){
        FunctionRole functionRole = new FunctionRole();
        functionRole.setFrId("");
        functionRole.setFrName("功能角色4");
        froleS.insert(functionRole);
    }

}
