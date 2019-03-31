package org.middlesoft.shiro.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the entity class of sys_role_permission
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermissionDto {

    @Id
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
