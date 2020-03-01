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
@ApiModel(value = "增加登陆档")
public class LoginInsertDto implements Serializable {

    private static final long serialVersionUID = -7545661228310059917L;

    @ApiModelProperty(value = "用户id",required = true)
    private String userId;

    @ApiModelProperty(value = "设备id",required = true)
    private String equipment;

}
