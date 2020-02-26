package com.soft.one.ewms.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "传输给前端的用户信息类")
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
    @ApiModelProperty(value = "json传输忽略")
    private String userPsw;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户手机，11位
     */
    @ApiModelProperty(value = "用户手机，11位")
    private String tel;

    /**
     * 功能角色ID
     */
    @ApiModelProperty(value = "功能角色id")
    private String frId;

    /**
     * frId中文
     */
    @ApiModelProperty(value = "功能角色中文描述，前端显示这个，不显示id")
    private String frIdDescription;

    /**
     * 操作资料角色ID
     */
    @ApiModelProperty(value = "操作资料角色ID")
    private String roidId;

    /**
     * roidId中文描述
     */
    @ApiModelProperty(value = "操作资料角色中文描述，前端显示这个，不显示id")
    private String roidIdDescription;

    /**
     * 密码更改日期与时间，yyyy-mm-dd hh:mm
     */
    @ApiModelProperty(value = "密码更改日期与时间，Date类型，精确到秒，yyyy-mm-dd hh:mm:ss")
    private Date pswDate;

}
