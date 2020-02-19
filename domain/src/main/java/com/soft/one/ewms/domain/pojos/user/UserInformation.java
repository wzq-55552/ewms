package com.soft.one.ewms.domain.pojos.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_information")
public class UserInformation implements Serializable {
    /**
     * 用户ID,至少6个字符
     */
    @Id
    @Column(name = "User_id")
    private String userId;

    /**
     * 用户密码,至少為八個字符. 需由大寫英文, 小寫英文, 數字及符號4項中的其中3項組成
     */
    @Column(name = "User_psw")
    private String userPsw;

    /**
     * 用户姓名
     */
    @Column(name = "User_name")
    private String userName;

    /**
     * 用户手机，11位
     */
    @Column(name = "Tel")
    private String tel;

    /**
     * 功能角色ID
     */
    @Column(name = "Fr_id")
    private String frId;

    /**
     * 操作资料角色ID
     */
    @Column(name = "Roid_id")
    private String roidId;

    /**
     * 密码更改日期与时间，yyyy-mm-dd hh:mm
     */
    @Column(name = "Psw_date")
    private Date pswDate;

    private static final long serialVersionUID = 1L;
}