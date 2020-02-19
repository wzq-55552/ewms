package com.soft.one.ewms.domain.pojos.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LmsWarehouseAcc {

    // 仓库号
    private String accNo;

    // 仓库类型
    //1.自营
    //2.联营
    //3,加盟
    //4,外判
    //5,网购
    private int accType;
}
