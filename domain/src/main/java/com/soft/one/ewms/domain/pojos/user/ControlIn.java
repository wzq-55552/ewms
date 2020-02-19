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
     * 用户ID,用于判断账户是否已登陆
     */
    @Column(name = "User_id")
    private String userId;

    private static final long serialVersionUID = 1L;
}