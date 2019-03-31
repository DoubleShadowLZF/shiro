package org.middlesoft.shiro.config.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.middlesoft.shiro.config.exception.SystemException;
import org.middlesoft.shiro.entity.UiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 对系统自定义的异常统一处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class SystemExceptionHandler {

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
