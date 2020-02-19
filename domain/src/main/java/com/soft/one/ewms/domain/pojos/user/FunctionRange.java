package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "function_range")
public class FunctionRange implements Serializable {
    /**
     * 功能角色ID
     */
    @Column(name = "Fr_id")
    private String frId;

    /**
     * 功能ID
     */
    @Column(name = "F_id")
    private String fId;

    private static final long serialVersionUID = 1L;
}