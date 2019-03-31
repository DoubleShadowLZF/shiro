package org.middlesoft.shiro.config.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.middlesoft.shiro.entity.ErrorEnum;

/**
 * 系统统一异常
 */
public class SystemException extends RuntimeException {
    @Getter
    private JSONObject exceptionJson;

    @Getter
    private Integer code;
    @Getter
    private String message;

    public SystemException() {
        super();
    }

    public SystemException(String exceptionMsg) {
        super(exceptionMsg);
    }

    /**
     * 传入json格式数据跑出异常
     *
     * @param json
     */
    public SystemException(JSONObject json) {
        this.exceptionJson = json;
    }

    /**
     * 根据错误编码跑出异常
     *
     * @param errorEnum
     */
    public SystemException(ErrorEnum errorEnum) {
        this.code = errorEnum.getErrorCode();
        this.message = errorEnum.getErrorMessage();
        this.exceptionJson.put(errorEnum.getErrorCode() + "", errorEnum.getErrorMessage());
    }
}
