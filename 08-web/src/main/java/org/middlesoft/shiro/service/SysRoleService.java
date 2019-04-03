package org.middlesoft.shiro.service;

import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.middlesoft.shiro.entity.dto.SysRoleDto;
import java.util.List;

/**
 * 
 */
public interface SysRoleService {
//Define something special
    /**
     * insert a item 
     * 
    */
    SysRoleDto add(SysRoleQo item);


    /**
     * delete a item 
     * 
    */
    long delete(SysRoleQo item);


    /**
     * edit item 
     * 
    */
    int edit(SysRoleQo item);


    /**
     * find items
     * 
    */
    List<SysRoleDto> find(SysRoleQo item);
}
