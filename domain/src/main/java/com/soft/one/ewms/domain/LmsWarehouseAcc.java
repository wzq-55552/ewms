package com.soft.one.ewms.domain;

import lombok.Data;

/**
 * Title：仓库类
 * Description：暂时不能测试，那自己写个api,只需要2个属性，不连数据库，数据后端定义就可以
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
public class LmsWarehouseAcc {

    //仓库号
    private String acc_no;

    //仓库类型
    //1.自營
    //2.聯營
    //3,加盟
    //4,外判
    //5,網購
    private Integer acc_type;

    public LmsWarehouseAcc(String acc_no, Integer acc_type) {
        this.acc_no = acc_no;
        this.acc_type = acc_type;
    }
    
    public LmsWarehouseAcc() {

    }


}
