package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 功能角色描述
     */
    @Column(name = "Description")
    private String description;

    /**
     * 功能角色名
     */
    @Column(name = "Fr_name")
    private String frName;

    private static final long serialVersionUID = -3717496584590047269L;
}