package org.middlesoft.shiro.config;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.middlesoft.shiro.entity.dto.QSysUserDto;
import org.middlesoft.shiro.entity.dto.SysUserDto;
import org.middlesoft.shiro.system.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public String getName() {
        return "userRealm";
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        QSysUserDto qu = QSysUserDto.sysUserDto;
        SysUserDto user = queryFactory.select(qu).from(qu).where(qu.username.eq(userName)).fetchOne();
        if (user == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        user.setPassword(null);
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, JSONObject.toJSON(user));
        return authenticationInfo;
    }

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        log.info("permission的值为：{}", permission);
        log.info("本用户的权限为：{}", permission.get("permissionList"));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((List) permission.get("permissionList"));
        return authorizationInfo;
    }


}
