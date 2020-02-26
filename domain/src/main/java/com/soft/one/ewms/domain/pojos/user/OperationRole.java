package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_role")
@ApiModel(value = "操作角色类，类似公司名")
public class OperationRole implements Serializable {

    /**
     * 操作资料角色ID
     */
    @Id
    @Column(name = "Roid_id")
    @ApiModelProperty(value = "操作资料角色ID")
    private String roidId;

    /**
     * 操作资料角色名，即公司名
     */
    @Column(name = "Roid_type")
    @ApiModelProperty(value = "操作资料角色名，即公司名")
    private String roidType;

    private static final long serialVersionUID = -3721523069722106224L;
}