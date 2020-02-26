package com.soft.one.ewms.domain.dtos.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "管理员增加时间参数档")
public class TimeInsertDto implements Serializable {

    private static final long serialVersionUID = 5335853133172362359L;

    /**
     * 用户ID,记录修改参数的管理员
     */
    @ApiModelProperty(value = "用户ID,记录修改参数的管理员")
    private String userId;

    /**
     * 画面停留时间，以分为单位
     */
    @ApiModelProperty(value = "画面停留时间，以分为单位")
    private Integer stayTime;

    /**
     * 定期修改密码提醒时间，以天为单位
     */
    @ApiModelProperty(value = "定期修改密码提醒时间，以天为单位")
    private Integer remindTime;

}
