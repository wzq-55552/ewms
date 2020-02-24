package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "control_in")
public class ControlIn implements Serializable {
    /**
     * 自增序号
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID,用于判断账户是否已登陆
     */
    @Column(name = "User_id")
    private String userId;

    /**
     * 电脑/手机的MAC地址
     */
    @Column(name = "Equipment")
    private String equipment;

    private static final long serialVersionUID = 1074776062277916934L;
}