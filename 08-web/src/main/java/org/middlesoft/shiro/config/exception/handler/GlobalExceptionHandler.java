package org.middlesoft.shiro.config.exception.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.middlesoft.shiro.config.exception.SystemException;
import org.middlesoft.shiro.entity.ErrorEnum;
import org.middlesoft.shiro.entity.UiResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常拦截器
     * @param e
     * @return 400错误
     */
    @ExceptionHandler(Exception.class)
    public UiResult defaultErrorHandler(Exception e){
        String errorPosition = "";
        //异常对战中存在信息，则获取异常信息的位置
        if(e.getStackTrace().length > 0){
            StackTraceElement element = e.getStackTrace()[0];
            String fileName  = element.getFileName() == null ? "未找到错误文件":element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        UiResult uiResult = UiResult.getInstance(ErrorEnum.E_400.getErrorCode(), ErrorEnum.E_400.getErrorMessage());
        JSONObject errorJson = new JSONObject();
        errorJson.put("errorLocation",e.toString() + "\t错误位置"+errorPosition);
        uiResult.setInfo(errorJson);
        log.error("{}",e);
        return uiResult;
    }

    /**
     * GET/POST 请求方法错误的拦截器
     * 开发中较为常见，而且发生在进入Controller之前，
     * 上面的拦截器无法拦截到这个异常，
     * 因此需要定义该拦截器
     * @return 500错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public UiResult httpRequestMethodHandler(){
        return UiResult.getInstance(ErrorEnum.E_500.getErrorCode(),ErrorEnum.E_500.getErrorMessage());
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口，而连登录都没登录的时候，会报此错
     * @return 502错误
     */
    @ExceptionHandler(UnauthorizedException.class)
    public UiResult unauthorizedException(){
        return UiResult.getInstance(ErrorEnum.E_502.getErrorCode(),ErrorEnum.E_502.getErrorMessage());
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口，而连登录都还没登录的时候会报此错
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public UiResult unauthenticatedException(){
        return UiResult.getInstance(ErrorEnum.E_4011.getErrorCode(),ErrorEnum.E_4011.getErrorMessage());
    }

    /**
     * 系统统一处理异常
     * @param e 系统自定义的异常
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public UiResult systemException(SystemException e){
        return UiResult.getInstance(e.getCode(),e.getMessage());
    }

}
