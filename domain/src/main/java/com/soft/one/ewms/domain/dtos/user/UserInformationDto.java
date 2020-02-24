package com.soft.one.ewms.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationDto implements Serializable {

    private static final long serialVersionUID = 208859897019970820L;

    /**
     * 用户ID,至少6个字符
     */
    private String userId;

    /**
     * 用户密码,至少為八個字符. 需由大寫英文, 小寫英文, 數字及符號4項中的其中3項組成
     */
    @JsonIgnore // json传输忽略
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
     * frId中文
     */
    private String frIdDescription;

    /**
     * 操作资料角色ID
     */
    private String roidId;

    /**
     * roidId中文描述
     */
    private String roidIdDescription;

    /**
     * 密码更改日期与时间，yyyy-mm-dd hh:mm
     */
    private Date pswDate;

}
