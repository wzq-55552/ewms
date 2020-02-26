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
@Table(name = "function_menu")
@ApiModel(value = "功能类")
public class FunctionMenu implements Serializable {
    /**
     * 功能ID
     */
    @Id
    @Column(name = "F_id")
    @ApiModelProperty(value = "功能ID")
    private String fId;


    /**
     * 功能描述
     */
    @Column(name = "Description")
    @ApiModelProperty(value = "功能描述，前端需要显示这个")
    private String description;

    /**
     * 链接地址
     */
    @Column(name = "F_url")
    @ApiModelProperty(value = "链接地址")
    private String fUrl;

    /**
     * 功能名
     */
    @Column(name = "F_name")
    @ApiModelProperty(value = "功能名，我权限时需要用到的，前端增加数据的时候需要传给我")
    private String fName;

    private static final long serialVersionUID = -6001080466893842579L;
}