package org.middlesoft.shiro.service;

import org.middlesoft.shiro.entity.qo.SysRolePermissionQo;
import org.middlesoft.shiro.entity.dto.SysRolePermissionDto;
import java.util.List;

/**
 * 
 */
public interface SysRolePermissionService {
//Define something special
    /**
     * insert a item 
     * 
    */
    int add(SysRolePermissionQo item);


    /**
     * delete a item 
     * 
    */
    int delete(SysRolePermissionQo item);


    /**
     * edit item 
     * 
    */
    int edit(SysRolePermissionQo item);


    /**
     * find items
     * 
    */
    List<SysRolePermissionDto> find(SysRolePermissionQo item);
}
