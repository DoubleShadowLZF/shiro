package org.middlesoft.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * 认证demo
 */
@Slf4j
public class AuthenticationDemo {

    public static void main(String[] args) {
        //1.创建SecurityManaget工厂读取相应的配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.通过 SecurityManager 工厂获取SecurityManager 实例
        SecurityManager securityManager = factory.getInstance();
        //3.通过SecurityManager 对象设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4.通过SecurityUtils 获取主题 Subject
        Subject subject = SecurityUtils.getSubject();
        try{

            //5.假如登录的用户名 double 和 Double123，这个地方的double 和 Double123 表示用户登录时输入的信息
            //而 shiro.ini 文件终止信息相当于数据库中存放的用户信息
            UsernamePasswordToken token = new UsernamePasswordToken("double", "Double123");
            //6.进行用户身份验证
            subject.login(token);
            //7.通过subject 来判断用户是否通过验证
            if(subject.isAuthenticated()){
                log.debug("用户登录成功");
            }
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

    }
}
