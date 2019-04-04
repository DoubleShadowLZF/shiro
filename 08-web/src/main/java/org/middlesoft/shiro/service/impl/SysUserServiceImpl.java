package org.middlesoft.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.middlesoft.shiro.dao.SysPermissionRepository;
import org.middlesoft.shiro.dao.SysRolePermissionRepository;
import org.middlesoft.shiro.dao.SysRoleRepository;
import org.middlesoft.shiro.entity.PageResult;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.dto.*;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.middlesoft.shiro.entity.qo.SysRolePermissionQo;
import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.middlesoft.shiro.entity.qo.SysUserQo;
import org.middlesoft.shiro.dao.SysUserRepository;
import org.middlesoft.shiro.service.SysUserService;
import org.middlesoft.shiro.util.Sort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 *
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private SysRoleRepository roleRepository;

    @Autowired
    private SysRolePermissionRepository rolePermissionRepository;

    @Autowired
    private SysPermissionRepository permissionRepository;

    /**
     * 添加单个用户
     *
     * @param item
     * @return
     */
    public SysUserDto add(SysUserQo item) {
        SysUserQo userQo = new SysUserQo();
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(userQo, sysUserDto);
        return sysUserRepository.save(sysUserDto);
    }

    /**
     * delete a item
     */
    public long delete(SysUserQo item) {
        QSysUserDto qu = QSysUserDto.sysUserDto;
        return queryFactory.delete(qu).where(qu.id.eq(item.getId())).execute();
    }

    /**
     * edit item
     */
    public long edit(SysUserQo item) {
        QSysUserDto qu = QSysUserDto.sysUserDto;
        JPAUpdateClause where = queryFactory.update(qu).set(qu.nickname, item.getNickname()).set(qu.roleId, item.getRoleId()).where(qu.id.eq(item.getId()));
        if (item.getPassword() != null) {
            where.set(qu.password, item.getPassword());
        }
        return where.execute();
    }

    /**
     * 获取用户列表
     *
     * @param query
     * @return
     */
    @Override
    public PageResult list(SysUserQo query) {
        Integer pageNum = query.getPageNum();
        Integer pageRow = query.getPageRow();
        Integer offset = (pageNum - 1) * pageRow;
        QSysUserDto qu = QSysUserDto.sysUserDto;
        QSysRoleDto qr = QSysRoleDto.sysRoleDto;
        QueryResults<Tuple> queryResults = queryFactory.select(qu.id, qu.nickname, qu.username, qr.roleName, qu.createTime, qu.updateTime).from(qu).offset(offset).limit(pageRow)
                .leftJoin(qr).on(qr.id.eq(qu.roleId))
                .orderBy(qu.createTime.desc()).fetchResults();
        List<Tuple> results = queryResults.getResults();
        List result = new ArrayList();
        if (results != null) {
            results.forEach(item -> {
                Long id = item.get(0, Long.class);
                String nickName = item.get(1, String.class);
                String userName = item.get(2, String.class);
                String roleName = item.get(3, String.class);
                Timestamp createTime = item.get(4, Timestamp.class);
                Timestamp updateTime = item.get(5, Timestamp.class);
                JSONObject json = new JSONObject();
                json.put("id", id);
                json.put("nickname", nickName);
                json.put("username", userName);
                json.put("roleName", roleName);
                json.put("createTime", createTime);
                json.put("updateTime", updateTime);
                result.add(json);
            });
        }
        return PageResult.ok((int) (queryResults.getTotal() / pageRow + 1), (int) queryResults.getTotal(), result);
    }

    /**
     * 获取角色下拉框
     *
     * @return
     */
    @Override
    public UiResult getRoleLabel() {
        QSysRoleDto qr = QSysRoleDto.sysRoleDto;
        List<SysRoleDto> roles = queryFactory.select(qr).from(qr).fetch();
        List result = new ArrayList();
        roles.forEach(role -> {
            Map map = new HashMap<>();
            map.put("roleId", role.getId());
            map.put("roleName", role.getRoleName());
            result.add(map);
        });
        Map map = new HashMap();
        map.put("list", result);
        return UiResult.ok(map);
    }

    @Override
    public List listRole() {
        QSysRoleDto qr = QSysRoleDto.sysRoleDto;
        QSysRolePermissionDto qrp = QSysRolePermissionDto.sysRolePermissionDto;
        QSysPermissionDto qp = QSysPermissionDto.sysPermissionDto;
        QSysUserDto qu = QSysUserDto.sysUserDto;
        List<SysRoleDto> roles = queryFactory.select(qr).from(qr).where(qr.deleteStatus.eq("1")).fetch();
        log.debug("role:{}", JSONObject.toJSON(roles));

        List retList = new ArrayList();
        for (SysRoleDto role : roles) {
            List menuList = new ArrayList();
            Map<String, Object> menuNameMap = new HashMap<>();
            Map item = new HashMap();
            item.put("roleId", role.getId());
            item.put("roleName", role.getRoleName());
            List<SysRolePermissionDto> rolePermissions = queryFactory.select(qrp).from(qrp).where(qrp.roleId.eq(role.getId()).and(qrp.deleteStatus.eq("1"))).fetch();
            log.debug("rolePermissions:{}", JSONObject.toJSON(rolePermissions));
            String menuCode;
            String menuName;
            for (SysRolePermissionDto rolePermission : rolePermissions) {
                log.debug("rolePermission:{}", JSONObject.toJSON(rolePermission));
                Long permissionId = rolePermission.getPermissionId();
                SysPermissionDto permission = queryFactory.select(qp).from(qp).where(qp.id.eq(permissionId)).orderBy(qp.menuName.asc()).fetchOne();
                log.debug("permission:{}", JSONObject.toJSON(permission));
                Object cache = menuNameMap.get(permission.getMenuName());
                if (cache == null) {
                    List permissionsList = new ArrayList();
                    Map permissionMap = new HashMap();
                    permissionMap.put("permissionId", permission.getId());
                    permissionMap.put("permissionName", permission.getPermissionName());
                    permissionsList.add(permissionMap);
                    Map menusMap = new HashMap();
                    menuCode = permission.getMenuCode();
                    menuName = permission.getMenuName();
                    menusMap.put("menuCode", menuCode);
                    menusMap.put("menuName", menuName);
                    menusMap.put("permissions", permissionsList);
                    menuList.add(menusMap);
                    menuNameMap.put(menuName, menusMap);
                } else {
                    Map cacheMap = (Map) cache;
                    List permissions = (List) cacheMap.get("permissions");
                    Map permissionMap = new HashMap();
                    permissionMap.put("permissionId", permission.getId());
                    permissionMap.put("permissionName", permission.getPermissionName());
                    permissions.add(permissionMap);
                }
            }
            item.put("menus", menuList);
            List userList = new ArrayList();
            List<SysUserDto> users = queryFactory.select(qu).from(qu).where(qu.roleId.eq(role.getId())).fetch();
            log.debug("users:{}", users);
            for (SysUserDto user : users) {
                Map userMap = new HashMap();
                userMap.put("nickname", user.getNickname());
                userMap.put("userId", user.getId());
                userList.add(userMap);
            }
            item.put("users", userList);
            retList.add(item);
        }
        return retList;
    }

    @Override
    public List listAllPermission() {
        List list = new ArrayList();
        QSysPermissionDto qp = QSysPermissionDto.sysPermissionDto;
        List<SysPermissionDto> permissions = queryFactory.select(qp).from(qp).fetch();
        String menuName = "";
        List permissionList = new ArrayList();
        Map retMap = null;
        for (SysPermissionDto permission : permissions) {
            if (!permission.getMenuName().equals(menuName)) {
                permissionList = new ArrayList();
                menuName = permission.getMenuName();
                retMap = new HashMap();
                retMap.put("permissions", permissionList);
                retMap.put("menuName", menuName);
                list.add(retMap);
            }
            Map permissionMap = new HashMap();
            permissionMap.put("requiredPerm", permission.getRequiredPermission());
            permissionMap.put("id", permission.getId());
            permissionMap.put("permissionName", permission.getPermissionName());
            permissionList.add(permissionMap);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRoleDto addRole(SysRoleQo query) {
        Boolean isUpdate = query.getRoleId() != null ? true : false;
        List<Integer> permissions = query.getPermissions();
        SysRoleDto sysRoleDto = new SysRoleDto();
        BeanUtils.copyProperties(query, sysRoleDto);
        sysRoleDto.setDeleteStatus("1");
        SysRoleDto save = roleRepository.save(sysRoleDto);
        Long roleId = save.getId();
        //修改操作
        if (isUpdate) {
            SysRolePermissionQo whereRolePermission = new SysRolePermissionQo();
            whereRolePermission.setRoleId(query.getRoleId());
            List<SysRolePermissionDto> rolePermissions = rolePermissionRepository.findAll(rolePermissionRepository.where(whereRolePermission));

            /*Collections.sort(rolePermissions, (o1, o2) ->
                    o1.getPermissionId() > o2.getPermissionId() ? 1 : -1
            );
            Collections.sort(permissions, (o1, o2) ->
                    o1 > o2 ? 1 : -1);*/

            /*List logPermission = new ArrayList();
            for (int i = 0; i < rolePermissions.size(); i++) {
                logPermission.add(rolePermissions.get(i).getPermissionId());
            }
            log.debug("添加的全部权限：{}", permissions);
            log.debug("查询到的全部数据：{}", logPermission);*/

            Sort<SysRolePermissionDto> sort = new Sort<>();
            sort.removeSameObject(permissions,rolePermissions,"permissionId",(o1, o2) ->
                    o1.getPermissionId() > o2.getPermissionId() ? 1 : -1);

            List logPermission = new ArrayList();
            for (int i = 0; i < rolePermissions.size(); i++) {
                logPermission.add(rolePermissions.get(i).getPermissionId());
            }
            log.debug("去重后的查询到的permission：{}", logPermission);
            log.debug("去重后的新增的permission:{}", permissions);
            for (SysRolePermissionDto rolePermissionDto:rolePermissions) {
                rolePermissionDto.setDeleteStatus("2");
                rolePermissionRepository.save(rolePermissionDto);
            }
        }
        //新增操作
        for (Integer permissionId : permissions) {
            //修改操作中，可能存在已添加的权限编码
            SysRolePermissionDto rolePermissionDto = new SysRolePermissionDto();
            boolean b = permissionRepository.existsById(Long.valueOf(permissionId + ""));
            if (!b) continue;
            rolePermissionDto.setRoleId(roleId).setPermissionId(Long.valueOf(String.valueOf(permissionId))).setDeleteStatus("1");
            rolePermissionRepository.save(rolePermissionDto);
        }
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteRole(SysRoleQo query) {
        QSysRoleDto qr = QSysRoleDto.sysRoleDto;
        queryFactory.update(qr).set(qr.deleteStatus, "2").where(qr.id.eq(query.getRoleId())).execute();
        QSysRolePermissionDto qrp = QSysRolePermissionDto.sysRolePermissionDto;
        queryFactory.update(qrp).set(qrp.deleteStatus, "2").where(qrp.roleId.eq(query.getRoleId())).execute();
        return query.getRoleId();
    }
}
