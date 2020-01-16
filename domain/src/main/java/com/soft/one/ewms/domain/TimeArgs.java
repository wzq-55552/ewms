package com.soft.one.ewms.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：时间参数表
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeArgs implements Serializable {
    /**
    * 用户ID,记录修改参数的管理员
    */
    private String User_id;

    /**
    * 记录修改参数的时间，yyyy-mm-dd hh:mm
    */
    private Date Args_date;

    /**
    * 画面停留时间，以分为单位
    */
    private Integer Stay_time;

    /**
    * 定期修改密码提醒时间，以天为单位
    */
    private Integer Remind_time;

    private static final long serialVersionUID = 1L;
}