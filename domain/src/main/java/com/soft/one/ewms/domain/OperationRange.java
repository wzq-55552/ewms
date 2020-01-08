package com.soft.one.ewms.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title：
 * Description：控制操作资料角色的权限
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_range")
public class OperationRange implements Serializable {
    /**
     * 操作资料角色ID
     */
    @Id
    @Column(name = "Roid_id")
    private String roidId;

    /**
     * 操作资料权限
     */
    @Id
    @Column(name = "Acc_no")
    private String accNo;

    private static final long serialVersionUID = 1L;
}