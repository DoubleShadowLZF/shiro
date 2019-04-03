package org.middlesoft.shiro.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.middlesoft.shiro.entity.dto.QSysRoleDto;
import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.middlesoft.shiro.entity.dto.SysRoleDto;
import org.middlesoft.shiro.dao.SysRoleRepository;
import org.middlesoft.shiro.service.SysRoleService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private JPAQueryFactory queryFactory;

    /**
     * insert a item 
     * 
    */
    public SysRoleDto add(SysRoleQo item){
        SysRoleDto sysRoleDto = new SysRoleDto();
        BeanUtils.copyProperties(item,sysRoleDto);
        return sysRoleRepository.save(sysRoleDto);
    }

    /**
     * delete a item 
     * 
    */
    public long delete(SysRoleQo item){
        QSysRoleDto qr = QSysRoleDto.sysRoleDto;
        return queryFactory.delete(qr).where(qr.id.eq(item.getId())).execute();
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
