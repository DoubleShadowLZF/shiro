package org.middlesoft.shiro.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class UiResult {

    /**
     * msg : 请求成功
     * code : 100
     * info : {"totalPage":1,"list":[],"totalCount":5}
     */

    private String msg;
    private Integer code;
    private Object info;

    protected static final Integer CODE_SUCCESS = 100;
    protected static final Integer CODE_FAIL = 200;

    public UiResult(){
        msg = "请求成功";
        code = CODE_SUCCESS;
    }

    protected static UiResult uiResult = new UiResult();

    public static UiResult ok(Object data){
        uiResult.setInfo(data);
        return uiResult;
    }
    public static UiResult ok( ){
        uiResult.setInfo(null);
        return uiResult;
    }

    public static UiResult fail() {
        uiResult.setCode(CODE_FAIL);
        uiResult.setMsg("请求失败");
        return uiResult;
    }
}
