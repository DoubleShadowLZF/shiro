package org.middlesoft.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 认证：
 *  用户名为“Double”的用户密码为“Double123”，其他用户的密码为“1234”
 *  授权：
 *      不处理
 */
@Slf4j
public class MyRealm  extends AuthorizingRealm {
    /**
     * 盐值
     */
    String salt = "Double";
    Integer iteration = 2;
    @Override
    public String getName() {
        return "myRealm";
    }

    /**
     * 认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  userName = (String) authenticationToken.getPrincipal();
        log.info("用户名为{}",userName);
        //从数据库根据用户名取出密码
        String pwd = getPwdFromDb(userName);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, pwd, ByteSource.Util.bytes(salt),getName());
        return info;
    }

    /**
     * 授权信息
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 从数据库中取出密码
     * @param userName
     * @return
     */
    private String getPwdFromDb(String userName){
        String pwd = null;
        if(userName.contains("Double")){
            pwd = "Double123";
        }else{
            pwd = "1234";
        }
        pwd = new SimpleHash("md5", pwd, salt, iteration).toString();
        log.info("加密后密码：{}",pwd);
        return pwd;
    }
}
