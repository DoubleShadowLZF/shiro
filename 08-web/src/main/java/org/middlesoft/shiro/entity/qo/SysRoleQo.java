package org.middlesoft.shiro.entity.qo;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * the entity class of sys_role
 */
@Data
@Accessors(chain = true)
public class SysRoleQo {

    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    private java.sql.Timestamp createTime;

    private java.sql.Timestamp updateTime;

    /**
     * 是否有效  1有效  2无效
     */
    private String deleteStatus;

}
