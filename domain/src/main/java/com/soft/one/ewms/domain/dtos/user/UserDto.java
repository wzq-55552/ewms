package com.soft.one.ewms.domain.dtos.user;

import lombok.Data;

@Data
public class UserDto {
    /**
     * 用户ID,至少6个字符
     */
    private String userId;

    /**
     * 用户密码,至少為八個字符. 需由大寫英文, 小寫英文, 數字及符號4項中的其中3項組成
     */
    private String userPsw;

    /**
     * 用户姓名
     */
    private String userName;
}
