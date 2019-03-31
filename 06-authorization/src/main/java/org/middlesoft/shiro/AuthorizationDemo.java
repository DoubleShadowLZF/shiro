package org.middlesoft.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class AuthorizationDemo {

    public static void main(String[] args) {
        String userName = "Double";
        String role = "role2";
        String permission = "user:add";

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        //root double
        UsernamePasswordToken token = new UsernamePasswordToken(userName, "Double123");
        try{
            subject.login(token);
            if(subject.isAuthenticated()){
                log.info("用户登录成功");
            }
        }catch (AuthenticationException e){
            e.printStackTrace();
            log.error("用户登录失败：{}",e.getMessage());
        }
        //基于角色的授权
        //判断用户是否具有某个角色
        boolean hasRole = subject.hasRole(role);
        log.debug("{}是否具有角色{}的权限：{}",userName,role,hasRole);

        //判断用户是否具有某个角色 与hasRole 功能相同，但是不具有权限时，以抛出异常的形式表现
//        subject.checkRole(role);

        //基于资源的授权
        boolean permitted = subject.isPermitted(permission);
        log.debug("用户{}是否进行{}的权限：{}",userName,permission,permitted);
        subject.checkPermission(permission);
        boolean permittedAll = subject.isPermittedAll("user:add", "user:delete", "user:update");

        List<String> permissionList = Arrays.asList("user:add", "user:delete", "user:update");
        log.debug("用户{}是否进行{}的权限：{}",userName,permissionList,permittedAll);

        subject.checkPermission(permission);
        subject.checkPermissions("user:add", "user:delete", "user:update");

        permission = "user:select";
        permitted = subject.isPermitted(permission);
        log.debug("用户{}是否进行{}的权限：{}",userName,permission,permitted);
    }
}
