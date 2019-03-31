package org.middlesoft.shiro.entity.qo;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * the entity class of sys_role_permission
 */
@Data
@Accessors(chain = true)
public class SysRolePermissionQo {

    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    private java.sql.Timestamp createTime;

    private java.sql.Timestamp updateTime;

    /**
     * 是否有效 1有效     2无效
     */
    private String deleteStatus;

}
