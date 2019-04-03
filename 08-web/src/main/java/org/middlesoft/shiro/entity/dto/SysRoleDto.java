package org.middlesoft.shiro.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * the entity class of sys_role
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_role")
public class SysRoleDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(insertable = false,updatable = false)
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    @Column(insertable = false,updatable = false)
    private java.sql.Timestamp createTime;

    @Column(insertable = false,updatable = false)
    private java.sql.Timestamp updateTime;

    /**
     * 是否有效  1有效  2无效
     */
    private String deleteStatus="1";

}
