package com.soft.one.ewms.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：控制登录表
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlIn implements Serializable {
    /**
     * 用户ID,用于判断账户是否已登陆
     */
    private String userId;

    private static final long serialVersionUID = 1L;

}