package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time_args")
public class TimeArgs implements Serializable {
    /**
     * 用户ID,记录修改参数的管理员
     */
    @Column(name = "User_id")
    private String userId;

    /**
     * 记录修改参数的时间，yyyy-mm-dd hh:mm
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "Args_date")
    private Date argsDate;

    /**
     * 画面停留时间，以分为单位
     */
    @Column(name = "Stay_time")
    private Integer stayTime;

    /**
     * 定期修改密码提醒时间，以天为单位
     */
    @Column(name = "Remind_time")
    private Integer remindTime;

    private static final long serialVersionUID = -7084848380161977136L;
}