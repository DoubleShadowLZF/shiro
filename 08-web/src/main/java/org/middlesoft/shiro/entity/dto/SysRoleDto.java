package org.middlesoft.shiro.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the entity class of sys_role
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_role")
public class SysRoleDto {

    @Id
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
