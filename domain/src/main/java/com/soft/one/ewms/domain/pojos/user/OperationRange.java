package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_range")
public class OperationRange implements Serializable {
    /**
     * 操作资料角色ID
     */
    @Column(name = "Roid_id")
    private String roidId;

    /**
     * 操作资料权限
     */
    @Column(name = "Acc_no")
    private String accNo;

    private static final long serialVersionUID = 1L;
}