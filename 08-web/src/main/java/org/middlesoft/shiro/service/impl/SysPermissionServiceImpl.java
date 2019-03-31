package org.middlesoft.shiro.service.impl;

import org.middlesoft.shiro.entity.qo.SysPermissionQo;
import org.middlesoft.shiro.entity.dto.SysPermissionDto;
import org.middlesoft.shiro.dao.SysPermissionRepository;
import org.middlesoft.shiro.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    /**
     * insert a item 
     * 
    */
    public int add(SysPermissionQo item){
    return 0;
    }

    /**
     * delete a item 
     * 
    */
    public int delete(SysPermissionQo item){
    return 0;
    }

    /**
     * edit item 
     * 
    */
    public int edit(SysPermissionQo item){
    return 0;
    }

    /**
     * find items
     * 
    */
   public List<SysPermissionDto> find(SysPermissionQo item){
    return null;
    }
}
