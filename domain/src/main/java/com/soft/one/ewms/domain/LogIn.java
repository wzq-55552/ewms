package com.soft.one.ewms.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：登录记录表
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogIn implements Serializable {
    /**
    * 自增序号
    */
    private Long firstId;

    /**
    * 用户ID
    */
    private String userId;

    /**
    * 电脑/手机的MAC地址
    */
    private String equipment;

    /**
    * 登入时间，yyyy-mm-dd hh:mm
    */
    private Date inDate;

    /**
    * 登出时间，yyyy-mm-dd hh:mm
    */
    private Date outDate;

    /**
    * 登出触发者，0：用户；1：系统
    */
    private Byte outType;

    private static final long serialVersionUID = 1L;
}