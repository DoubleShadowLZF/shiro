package org.middlesoft.shiro.controller;

import org.middlesoft.shiro.entity.ErrorEnum;
import org.middlesoft.shiro.entity.UiResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    /**
     * 主要是登陆后的各种错误路径
     * 404页面改为返回此json
     * 未登录的情况下，大部分接口都已经被shiro拦截
     * 返回让用户登录了
     *
     * @return 501错误json信息
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public UiResult handleError() {
        return UiResult.getInstance(ErrorEnum.E_501.getErrorCode(), ErrorEnum.E_501.getErrorMessage());
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
