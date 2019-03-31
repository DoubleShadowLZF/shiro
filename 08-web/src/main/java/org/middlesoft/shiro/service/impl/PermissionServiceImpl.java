package org.middlesoft.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.middlesoft.shiro.entity.dto.*;
import org.middlesoft.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private JPAQueryFactory queryFactory;

    /**
     * 根据用户名获取授权信息
     *
     * @param username
     * @return
     */
    @Override
    public JSONObject getUserPermission(String username) {
        QSysPermissionDto qp = QSysPermissionDto.sysPermissionDto;
        QSysRoleDto qr = QSysRoleDto.sysRoleDto;
        QSysRolePermissionDto qrp = QSysRolePermissionDto.sysRolePermissionDto;
        QSysUserDto qu = QSysUserDto.sysUserDto;
        BooleanBuilder bb = new BooleanBuilder();
        JSONObject result= new JSONObject();
        try {
            /*List<Tuple> fetch = queryFactory.select(qu.id, qu.nickname, qu.roleId, qr.roleName, qp.menuCode, qp.permissionCode).from(qp)
                    .leftJoin(qrp).on(bb.clone().and(qp.id.eq(qrp.permissionId)))
                    .leftJoin(qr).on(bb.clone().and(qrp.roleId.eq(qr.id)))
                    .leftJoin(qu).on(bb.clone().and(qu.roleId.eq(qrp.roleId)).and(qu.username.eq(username)).and(qu.deleteStatus.eq("1")))
                    .fetch();*/
            Tuple tuple = queryFactory.select(qu.id, qu.nickname, qu.roleId, qr.roleName, qp.menuCode, qp.permissionCode,qu.username).from(qu)
                    .leftJoin(qr).on(qu.roleId.eq(qr.id))
                    .leftJoin(qrp).on(qrp.roleId.eq(qr.id))
                    .leftJoin(qp).on(qp.id.eq(qrp.permissionId))
                    .where(bb.clone().and(qu.username.eq(username)).and(qu.deleteStatus.eq("1")))
                    .fetchOne();
            log.info("{}",tuple);
                result.put("userId",tuple.get(0,Long.class));
                result.put("nickname",tuple.get(1,String.class));
                result.put("roleId",tuple.get(2,Long.class));
                result.put("roleName",tuple.get(3,String.class));
                result.put("menuCode",tuple.get(4,String.class));
                result.put("permissionCode",tuple.get(5,String.class));
                result.put("username",tuple.get(6,String.class));
                Long roleId = tuple.get(2, Long.TYPE);
            //如果是管理员，查询所有菜单和所有权限
            if(roleId.equals(1L)){
                List<String> permissionCode = getPermissionCode(null);
                List<String> permissionMenu = getPermissionMenu(null);
                result.put("menuList",permissionMenu);
                result.put("permissionList",permissionCode);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取列表信息
     * @param requestPermission
     * @return
     */
    public List<String> getPermissionMenu(Long requestPermission){
        QSysPermissionDto qp = QSysPermissionDto.sysPermissionDto;
        Predicate where = requestPermission != null ? qp.requiredPermission.eq(requestPermission) :qp.requiredPermission.isNotNull();
        List<String> menuCode = queryFactory.select(qp.menuCode).from(qp).where(where).groupBy(qp.menuCode).fetch();
        log.debug("获取到的列表信息：{}",menuCode);
        return menuCode;
    }

    /**
     * 获取权限编码
     * @param requestPermission
     * @return
     */
    public List<String> getPermissionCode(Long requestPermission){
        QSysPermissionDto qp = QSysPermissionDto.sysPermissionDto;
        Predicate where = requestPermission != null ? qp.requiredPermission.eq(requestPermission):qp.requiredPermission.isNotNull();
        List<String> permissionCode = queryFactory.select(qp.permissionCode).from(qp).where(where).fetch();
        log.debug("permission code :{}",permissionCode);
        return permissionCode;
    }
}
