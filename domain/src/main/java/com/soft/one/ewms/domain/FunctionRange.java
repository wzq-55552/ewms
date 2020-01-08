package com.soft.one.ewms.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Title：
 * Description：对应的功能角色有什么功能
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "function_range")
public class FunctionRange implements Serializable {
    /**
     * 功能角色ID
     */
    @Id
    @Column(name = "Fr_id")
    private String frId;

    /**
     * 功能ID
     */
    @Id
    @Column(name = "F_id")
    private String fId;

    private static final long serialVersionUID = 1L;
}