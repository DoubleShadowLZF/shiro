package org.middlesoft.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

@Slf4j
public class MultiRealmsDemo {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        /*
            AllSuccessfulStrategy
                dbcRealm Double123          -> iniReaml 认证不成功
            FirstSuccessfulStrategy , AtLeastOneSuccessfulStrategy
                double Double123            -> iniReaml,jdbcReaml 认证成功
                jdbcReaml Double123            -> iniReaml,jdbcReaml 认证成功
         */
        UsernamePasswordToken token = new UsernamePasswordToken("double","Double123");
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
