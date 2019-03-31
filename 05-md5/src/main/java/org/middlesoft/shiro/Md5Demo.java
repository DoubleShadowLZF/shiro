package org.middlesoft.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

@Slf4j
public class Md5Demo {
    public static void main(String[] args) {
        String source = "123";
        String salt = "Double";
        //对 123 进行md5 加密
        Md5Hash hash = new Md5Hash(source);
        log.info("md5:{}",hash);
        //对 “123” 加盐值 “Double” md5加密
        hash = new Md5Hash(source,salt);
        log.info("salt:{}\nmd5:{}",salt,hash);
        //对 “123” 加盐值 “Double”，进行md5加密，并迭代两次
        hash = new Md5Hash(source,salt,2);
        log.info("salt:{}\nmd5:{}\niteration:{}",salt,hash,2);
    }
}
