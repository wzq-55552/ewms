package com.soft.one.ewms.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionDto implements Serializable {

    private static final long serialVersionUID = -3839947601151280317L;

    /**
     * 功能角色ID
     */
    private String frId;

    /**
     * 功能角色描述
     */
    private String description;

    /**
     * 功能角色名
     */
    private String frName;

    /**
     * 权限id集合
     */
    private List<String> fIds;

}
