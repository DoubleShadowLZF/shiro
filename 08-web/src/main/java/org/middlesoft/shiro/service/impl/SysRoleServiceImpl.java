package org.middlesoft.shiro.service.impl;

import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.middlesoft.shiro.entity.dto.SysRoleDto;
import org.middlesoft.shiro.dao.SysRoleRepository;
import org.middlesoft.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    /**
     * insert a item 
     * 
    */
    public int add(SysRoleQo item){
    return 0;
    }

    /**
     * delete a item 
     * 
    */
    public int delete(SysRoleQo item){
    return 0;
    }

    /**
     * edit item 
     * 
    */
    public int edit(SysRoleQo item){
    return 0;
    }

    /**
     * find items
     * 
    */
   public List<SysRoleDto> find(SysRoleQo item){
    return null;
    }
}
