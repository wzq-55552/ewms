package com.soft.one.ewms.api.controller;

import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.LmsWarehouseAcc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Title：暴露数据api
 * Description：现阶段只有一个api,仓库号和仓库类型，有1000个左右，测试用的，端口号8081，先启动
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@RestController
public class AccController {

    @GetMapping("/api/acc/all")
    public ResponseResult<List> accapi(){

        List<LmsWarehouseAcc> lmsWarehouseAccList = new ArrayList<>();

        //存数据,我们也不能改旭日那边的数据
        for (int i = 0; i < 10; i++) {

            String str1 = "AV0"+Integer.toString(i);
            String str2 = "BV0"+Integer.toString(i);
            String str3 = "CV0"+Integer.toString(i);
            String str4 = "DV0"+Integer.toString(i);
            String str5 = "EV0"+Integer.toString(i);

            for (int j = 1; j <= 5; j++) {
                LmsWarehouseAcc lmsWarehouseAcc1 = new LmsWarehouseAcc(str1,j);

                LmsWarehouseAcc lmsWarehouseAcc2 = new LmsWarehouseAcc(str2,j);

                LmsWarehouseAcc lmsWarehouseAcc3 = new LmsWarehouseAcc(str3,j);

                LmsWarehouseAcc lmsWarehouseAcc4 = new LmsWarehouseAcc(str4,j);

                LmsWarehouseAcc lmsWarehouseAcc5 = new LmsWarehouseAcc(str5,j);

                lmsWarehouseAccList.add(lmsWarehouseAcc1);
                lmsWarehouseAccList.add(lmsWarehouseAcc2);
                lmsWarehouseAccList.add(lmsWarehouseAcc3);
                lmsWarehouseAccList.add(lmsWarehouseAcc4);
                lmsWarehouseAccList.add(lmsWarehouseAcc5);
            }
        }

        return new ResponseResult<List>(0,"成功",lmsWarehouseAccList);
    }
}
