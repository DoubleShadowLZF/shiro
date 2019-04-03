package org.middlesoft.shiro.service;

import org.middlesoft.shiro.entity.PageResult;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.dto.SysRoleDto;
import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.middlesoft.shiro.entity.qo.SysUserQo;
import org.middlesoft.shiro.entity.dto.SysUserDto;
import java.util.List;

/**
 * 
 */
public interface SysUserService {
//Define something special
    /**
     * insert a item 
     * 
    */
    SysUserDto add(SysUserQo item);


    /**
     * delete a item 
     * 
    */
    long delete(SysUserQo item);


    /**
     * edit item 
     * 
    */
    long edit(SysUserQo item);

    PageResult list(SysUserQo query);

    UiResult getRoleLabel();

    List listRole();

    List listAllPermission();

    SysRoleDto addRole(SysRoleQo query);

    Long deleteRole(SysRoleQo query);
}
