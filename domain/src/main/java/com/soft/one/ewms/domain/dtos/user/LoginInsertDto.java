package com.soft.one.ewms.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInsertDto implements Serializable {

    private static final long serialVersionUID = -7545661228310059917L;

    private String userId;

    private String equipment;

}
