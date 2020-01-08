package com.soft.one.ewms.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：
 * Description：管理功能角色的，比如管理员，物流部
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "function_role")
public class FunctionRole implements Serializable {
    /**
     * 功能角色ID
     */
    @Id
    @Column(name = "Fr_id")
    private String frId;

    /**
     * 功能角色名
     */
    @Column(name = "Fr_name")
    private String frName;

    private static final long serialVersionUID = 1L;
}