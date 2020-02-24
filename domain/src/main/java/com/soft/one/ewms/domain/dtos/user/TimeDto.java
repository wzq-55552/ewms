package com.soft.one.ewms.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDto implements Serializable {

    private static final long serialVersionUID = 6946584260224787142L;

    /**
     * 用户密码更改日期与时间，yyyy-mm-dd hh:mm
     */
    private Date pswDate;

    /**
     * 定期修改密码提醒时间，以天为单位
     */
    private Integer remindTime;
}
