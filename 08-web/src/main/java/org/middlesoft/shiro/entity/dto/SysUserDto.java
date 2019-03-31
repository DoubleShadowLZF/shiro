package org.middlesoft.shiro.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the entity class of sys_user
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_user")
public class SysUserDto {

    @Id
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
