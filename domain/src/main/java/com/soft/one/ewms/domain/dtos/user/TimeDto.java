package com.soft.one.ewms.domain.dtos.user;

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
@ApiModel(value = "提醒修改密码参数档dto")
public class TimeDto implements Serializable {

    private static final long serialVersionUID = 6946584260224787142L;

    /**
     * 用户密码更改日期与时间，yyyy-mm-dd hh:mm
     */
    @ApiModelProperty(value = "用户密码更改日期与时间，给前端该用户的最后一次修改密码时间")
    private Date pswDate;

    /**
     * 定期修改密码提醒时间，以天为单位
     */
    @ApiModelProperty(value = "定期修改密码提醒时间，int的，表示天数")
    private Integer remindTime;
}
