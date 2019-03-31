package org.middlesoft.shiro.controller;

import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.qo.UserQo;
import org.middlesoft.shiro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录授权
     * @param user
     * @return
     */
    @PostMapping("/auth")
    public UiResult authLogin(@RequestBody UserQo user){
        return loginService.autuLogin(user);
    }

    /**
     * 获取当前登录用户的信息
     * @return
     */
    @PostMapping("/getInfo")
    public UiResult getInfo(){
        return loginService.getInfo();
    }

    /**
     * 退出当前用户
     * @return
     */
    @PostMapping
    public UiResult logout(){
        return loginService.logout();
    }


}
