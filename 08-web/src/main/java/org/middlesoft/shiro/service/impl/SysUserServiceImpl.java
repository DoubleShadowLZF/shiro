package org.middlesoft.shiro.service.impl;

import org.middlesoft.shiro.entity.qo.SysUserQo;
import org.middlesoft.shiro.entity.dto.SysUserDto;
import org.middlesoft.shiro.dao.SysUserRepository;
import org.middlesoft.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * insert a item 
     * 
    */
    public int add(SysUserQo item){
    return 0;
    }

    /**
     * delete a item 
     * 
    */
    public int delete(SysUserQo item){
    return 0;
    }

    /**
     * edit item 
     * 
    */
    public int edit(SysUserQo item){
    return 0;
    }

    /**
     * find items
     * 
    */
   public List<SysUserDto> find(SysUserQo item){
    return null;
    }
}
