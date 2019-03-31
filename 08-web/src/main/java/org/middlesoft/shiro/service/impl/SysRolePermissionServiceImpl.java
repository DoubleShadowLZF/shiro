package org.middlesoft.shiro.service.impl;

import org.middlesoft.shiro.entity.qo.SysRolePermissionQo;
import org.middlesoft.shiro.entity.dto.SysRolePermissionDto;
import org.middlesoft.shiro.dao.SysRolePermissionRepository;
import org.middlesoft.shiro.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Autowired
    private SysRolePermissionRepository sysRolePermissionRepository;

    /**
     * insert a item 
     * 
    */
    public int add(SysRolePermissionQo item){
    return 0;
    }

    /**
     * delete a item 
     * 
    */
    public int delete(SysRolePermissionQo item){
    return 0;
    }

    /**
     * edit item 
     * 
    */
    public int edit(SysRolePermissionQo item){
    return 0;
    }

    /**
     * find items
     * 
    */
   public List<SysRolePermissionDto> find(SysRolePermissionQo item){
    return null;
    }
}
