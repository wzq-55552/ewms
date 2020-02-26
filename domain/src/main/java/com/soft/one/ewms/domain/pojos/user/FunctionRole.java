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
@Table(name = "function_role")
@ApiModel(value = "功能角色类")
public class FunctionRole implements Serializable {

    /**
     * 功能角色ID
     */
    @Id
    @Column(name = "Fr_id")
    @ApiModelProperty(value = "功能角色ID")
    private String frId;

    /**
     * 功能角色描述
     */
    @Column(name = "Description")
    @ApiModelProperty(value = "前端显示的功能角色描述，不显示id")
    private String description;

    /**
     * 功能角色名
     */
    @Column(name = "Fr_name")
    @ApiModelProperty(value = "功能角色英文描述")
    private String frName;

    private static final long serialVersionUID = -3717496584590047269L;
}