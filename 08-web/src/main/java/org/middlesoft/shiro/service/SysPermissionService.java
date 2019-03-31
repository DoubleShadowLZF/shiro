package org.middlesoft.shiro.service;

import org.middlesoft.shiro.entity.qo.SysPermissionQo;
import org.middlesoft.shiro.entity.dto.SysPermissionDto;
import java.util.List;

/**
 * 
 */
public interface SysPermissionService {
//Define something special
    /**
     * insert a item 
     * 
    */
    int add(SysPermissionQo item);


    /**
     * delete a item 
     * 
    */
    int delete(SysPermissionQo item);


    /**
     * edit item 
     * 
    */
    int edit(SysPermissionQo item);


    /**
     * find items
     * 
    */
    List<SysPermissionDto> find(SysPermissionQo item);
}
