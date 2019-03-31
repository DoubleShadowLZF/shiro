package org.middlesoft.shiro.entity.qo;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * the entity class of sys_user
 */
@Data
@Accessors(chain = true)
public class SysUserQo {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private java.sql.Timestamp createTime;

    /**
     * 修改时间
     */
    private java.sql.Timestamp updateTime;

    /**
     * 是否有效  1有效  2无效
     */
    private String deleteStatus;

}
