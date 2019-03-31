package org.middlesoft.shiro.service;

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
    int add(SysUserQo item);


    /**
     * delete a item 
     * 
    */
    int delete(SysUserQo item);


    /**
     * edit item 
     * 
    */
    int edit(SysUserQo item);


    /**
     * find items
     * 
    */
    List<SysUserDto> find(SysUserQo item);
}
