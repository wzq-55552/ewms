package com.soft.one.ewms.domain.pojos.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LmsWarehouseAcc implements Serializable {

    private static final long serialVersionUID = 4693828660673641243L;

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
