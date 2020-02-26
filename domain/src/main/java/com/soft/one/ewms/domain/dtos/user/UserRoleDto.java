package com.soft.one.ewms.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分配用户角色dto,可修改，可删除，给后端空就是删除")
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = -6616534843779640762L;

    /**
     * 用户ID,至少6个字符
     */
    private String userId;

    /**
     * 功能角色ID
     */
    @ApiModelProperty(value = "功能角色ID")
    private String frId;

    /**
     * 操作资料角色ID
     */
    @ApiModelProperty(value = "操作资料角色ID")
    private String roidId;

}
