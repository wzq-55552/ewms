package com.soft.one.ewms.domain.dtos.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Title：当作dto,也可以当作es索引
 * Description：匹配字段
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "查询登录档dto")
public class LoginSearchDto implements Serializable {

    private static final long serialVersionUID = -1634086296792675144L;

    /**
     * 用户ID,至少6个字符
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "设备id")
    private String equipment;

    /**
     * 起始时间，yyyy-mm-dd hh:mm
     */
    @ApiModelProperty(value = "Date数据，起始时间，一个时间段来搜索,yyyy-mm-dd hh:mm:ss")
    private Date beginDate;

    /**
     * 结束时间，yyyy-mm-dd hh:mm
     */
    @ApiModelProperty(value = "结束时间，Date数据，一个时间段来搜索,yyyy-mm-dd hh:mm:ss")
    private Date endDate;
}
