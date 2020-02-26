package com.soft.one.ewms.domain.dtos.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户修改个人信息")
public class UserUpdateDto implements Serializable {

    private static final long serialVersionUID = 223783377312812574L;

    /**
     * 用户ID,至少6个字符
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 用户密码,至少為八個字符. 需由大寫英文, 小寫英文, 數字及符號4項中的其中3項組成
     */
    @ApiModelProperty(value = "用户密码")
    private String userPsw;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 用户手机，11位
     */
    @ApiModelProperty(value = "用户手机")
    private String tel;

}
