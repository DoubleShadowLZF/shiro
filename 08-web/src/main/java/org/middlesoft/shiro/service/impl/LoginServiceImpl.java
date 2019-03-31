package org.middlesoft.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.qo.UserQo;
import org.middlesoft.shiro.service.LoginService;
import org.middlesoft.shiro.service.PermissionService;
import org.middlesoft.shiro.system.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PermissionService permissionService;

    @Override
    public UiResult autuLogin(UserQo user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            JSONObject json = new JSONObject();
            json.put("result","success");
            return UiResult.ok(json);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return UiResult.fail();
        }
    }

    /**
     * 获取当前登录用户的权限信息
     * @return 返回用户的权限信息
     */
    @Override
    public UiResult getInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(SystemCode.SESSION_USER_INFO);
        String userName = (String) userInfo.get("username");
        //获取用户权限
        JSONObject userPermission = permissionService.getUserPermission(userName);
        session.setAttribute(SystemCode.SESSION_USER_PERMISSION,userPermission);
        JSONObject result = new JSONObject();
        result.put("userPermission",userPermission);
        return UiResult.ok(result);
    }

    @Override
    public UiResult logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return UiResult.ok();
    }
}
