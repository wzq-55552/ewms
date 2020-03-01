package com.soft.one.ewms.domain.dtos.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "操作资料角色dto，增加，修改")
public class OperationDto implements Serializable {

    private static final long serialVersionUID = 1437534345242326431L;

    /**
     * 操作资料角色ID
     */
    @ApiModelProperty(value = "操作资料角色ID，不可空",required = true)
    private String roidId;

    /**
     * 操作资料角色名，即公司名
     */
    @ApiModelProperty(value = "操作资料角色名")
    private String roidType;

    /**
     * 仓库权限集合
     */
    @ApiModelProperty(value = "仓库权限集合，空则表示无权限")
    private List<String> accNos;

}
