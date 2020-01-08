package com.soft.one.ewms.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Title：功能
 * Description：所有的功能
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
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
     * 功能名
     */
    @Column(name = "F_name")
    private String fName;

    /**
     * 链接地址
     */
    @Column(name = "F_url")
    private String fUrl;

    private static final long serialVersionUID = 1L;
}