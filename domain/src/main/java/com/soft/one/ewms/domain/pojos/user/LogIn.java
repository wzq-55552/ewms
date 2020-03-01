package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log_in")
@ApiModel(value = "登录记录档")
public class LogIn implements Serializable {
    /**
     * 自增序号
     */
    @Id
    @Column(name = "First_id")
    @GeneratedValue(generator = "JDBC")
    private Long firstId;

    /**
     * 用户ID
     */
    @Column(name = "User_id")
    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    /**
     * 电脑/手机的MAC地址
     */
    @Column(name = "Equipment")
    @ApiModelProperty(value = "设备ID",required = true)
    private String equipment;

    /**
     * 登入时间，yyyy-mm-dd hh:mm
     */
    @Column(name = "In_date")
    private Date inDate;

    /**
     * 登出时间，yyyy-mm-dd hh:mm
     */
    @Column(name = "Out_date")
    private Date outDate;

    /**
     * 登出触发者，0：用户；1：系统
     */
    @Column(name = "Out_type")
    private Integer outType;

    private static final long serialVersionUID = 7374409409461304062L;
}