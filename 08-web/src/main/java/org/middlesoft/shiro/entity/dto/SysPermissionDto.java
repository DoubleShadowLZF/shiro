package org.middlesoft.shiro.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the entity class of sys_permission
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_permission")
public class SysPermissionDto {

    /**
     * 自定id,主要供前端展示权限列表分类排序使用.
     */
    @Id
    private Long id;

    /**
     * 归属菜单,前端判断并展示菜单使用,
     */
    private String menuCode;

    /**
     * 菜单的中文释义
     */
    private String menuName;

    /**
     * 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    private String permissionCode;

    /**
     * 本权限的中文释义
     */
    private String permissionName;

    /**
     * 是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
     */
    private Long requiredPermission;

}
