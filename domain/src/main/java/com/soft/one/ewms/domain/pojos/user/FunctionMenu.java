package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "function_menu")
public class FunctionMenu implements Serializable {
    /**
     * 功能ID
     */
    @Id
    @Column(name = "F_id")
    private String fId;

    /**
     * 功能描述
     */
    @Column(name = "Description")
    private String description;

    /**
     * 链接地址
     */
    @Column(name = "F_url")
    private String fUrl;

    /**
     * 功能名
     */
    @Column(name = "F_name")
    private String fName;

    private static final long serialVersionUID = 1L;
}