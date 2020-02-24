package com.soft.one.ewms.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto implements Serializable {

    private static final long serialVersionUID = 1437534345242326431L;

    /**
     * 操作资料角色ID
     */
    private String roidId;

    /**
     * 操作资料角色名，即公司名
     */
    private String roidType;

    /**
     * 仓库权限集合
     */
    private List<String> accNos;

}
