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
@ApiModel(value = "登录退出参数，用户id和刷新令牌")
public class LogoutDto implements Serializable {

    private static final long serialVersionUID = -5062499493119662968L;

    /**
     * 用户ID,至少6个字符
     */
    @ApiModelProperty(value = "用户ID,至少6个字符",required = true)
    private String userId;

    /**
     * 刷新令牌
     */
    @ApiModelProperty(value = "刷新令牌",required = true)
    private String refreshToken;
}
