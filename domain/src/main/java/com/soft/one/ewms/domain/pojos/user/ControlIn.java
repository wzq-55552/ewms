package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "control_in")
@ApiModel(value = "控制档")
public class ControlIn implements Serializable {

    /**
     * 用户ID,用于判断账户是否已登陆
     */
    @Id
    @Column(name = "User_id")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 电脑/手机的MAC地址
     */
    @Column(name = "Equipment")
    @ApiModelProperty(value = "电脑/手机的MAC地址")
    private String equipment;

    private static final long serialVersionUID = 1074776062277916934L;
}