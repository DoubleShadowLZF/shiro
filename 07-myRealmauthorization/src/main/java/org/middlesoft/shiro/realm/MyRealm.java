package org.middlesoft.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.*;

/**
 * 认证：
 *  用户名为“Double”的用户密码为“Double123”，其他用户的密码为“1234”
 *  授权：
 *      不处理
 */
@Slf4j
public class MyRealm  extends AuthorizingRealm {

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
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, pwd,getName());
        return info;
    }

    /**
     * 授权信息
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:add");
        permissions.add("user:delete");
        permissions.add("user:edit");
        permissions.add("user:find");
        info.addStringPermissions(permissions);
        return info;
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
        return pwd;
    }

}
