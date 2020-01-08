package com.soft.one.ewms.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：用户表
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation implements Serializable {
    /**
    * 用户ID,至少6个字符
    */
    private String userId;

    /**
    * 用户密码,至少為八個字符. 需由大寫英文, 小寫英文, 數字及符號4項中的其中3項組成
    */
    private String userPsw;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 用户手机，11位
    */
    private String tel;

    /**
    * 功能角色ID
    */
    private String frId;

    /**
    * 操作资料角色ID
    */
    private String roidId;

    /**
    * 密码更改日期与时间，yyyy-mm-dd hh:mm
    */
    private Date pswDate;

    private static final long serialVersionUID = 1L;
}