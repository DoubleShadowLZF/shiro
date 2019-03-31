package org.middlesoft.shiro.service;

import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.qo.UserQo;

/**
 * 用户登录
 */
public interface LoginService {

    /**
     * 授权登录
     * @return
     */
    UiResult autuLogin(UserQo user);

    /**
     * 查询当前登录用户的信息
     * @return
     */
    UiResult getInfo();

    /**
     * 退出当前用户
     * @return
     */
    UiResult logout();

}
