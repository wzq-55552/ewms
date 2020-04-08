package com.soft.one.ewms.domain.dtos.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "功能角色DTO，增加修改功能角色都是传这些")
public class FunctionDto implements Serializable {

    private static final long serialVersionUID = -8029859748672637781L;

    /**
     * 功能角色ID
     */
    @ApiModelProperty(value = "功能角色id",required = true)
    private String frId;

    /**
     * 功能角色描述
     */
    @ApiModelProperty(value = "功能角色描述，需要填的，前端页面显示",required = true)
    private String description;

    /**
     * 功能角色名
     */
    @ApiModelProperty(value = "功能角色英文名，可空")
    private String frName;

    /**
     * 权限id集合
     */
    @ApiModelProperty(value = "角色的权限id集合，空的话则是无权限")
    private List<String> lists;

}
