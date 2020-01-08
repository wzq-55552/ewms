package com.soft.one.ewms.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：
 * Description：查看公司名
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_role")
public class OperationRole implements Serializable {
    /**
     * 操作资料角色ID
     */
    @Id
    @Column(name = "Roid_id")
    private String roidId;

    /**
     * 操作资料角色名，即公司名
     */
    @Column(name = "Roid_type")
    private String roidType;

    private static final long serialVersionUID = 1L;
}