package org.middlesoft.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

@Slf4j
public class MyRealWithMD5 {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Double","Double123");
        log.info("token:{}",token);
        subject.login(token);
        try {
            if (subject.isAuthenticated()) {
                log.info("用户登录成功");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            log.info("用户登录失败");
        }
    }
}
