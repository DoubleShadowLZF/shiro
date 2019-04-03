package org.middlesoft.shiro.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.middlesoft.shiro.entity.PageResult;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.dto.SysRoleDto;
import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.middlesoft.shiro.entity.qo.SysUserQo;
import org.middlesoft.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService userService;

    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public PageResult listUser(SysUserQo query){
        return userService.list(query);
    }

    @RequiresPermissions("role:list")
    @GetMapping("/getAllRoles")
    public UiResult roleLabel(){
        return userService.getRoleLabel();
    }

    @RequiresPermissions("user:update")
    @PostMapping
    public UiResult editUser(SysUserQo query){
        query.setId(query.getUserId());
        userService.edit(query);
        return UiResult.ok();
    }

    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public UiResult listRole(){
        List roles = userService.listRole();
        Map retMap = new HashMap();
        retMap.put("list",roles);
        return UiResult.ok(retMap);
    }

    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public UiResult listAllPermission(){
        List permissions = userService.listAllPermission();
        Map retMap = new HashMap();
        retMap.put("list",permissions);
        return UiResult.ok(retMap);
    }

    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public UiResult addRole(@RequestBody SysRoleQo query){
        SysRoleDto role = userService.addRole(query);
        return UiResult.ok(role);
    }

    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public UiResult updateRole(@RequestBody SysRoleQo query){
        query.setId(query.getRoleId());
        SysRoleDto role = userService.addRole(query);
        return UiResult.ok(role);
    }

    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public UiResult deleteRole(@RequestBody SysRoleQo query){
        Long id = userService.deleteRole(query);
        return UiResult.ok();
    }
}
