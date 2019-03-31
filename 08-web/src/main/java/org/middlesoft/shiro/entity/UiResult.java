package org.middlesoft.shiro.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
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

    public UiResult() {
        msg = "请求成功";
        code = CODE_SUCCESS;
    }


    protected static UiResult uiResult = new UiResult();

    public static UiResult getInstance(Integer code,String msg){
        UiResult result = new UiResult();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

    public static UiResult ok(Object data) {
        uiResult.setInfo(data);
        return uiResult;
    }

    public static UiResult ok() {
        uiResult.setInfo(null);
        return uiResult;
    }

    public static UiResult fail() {
        uiResult.setCode(CODE_FAIL);
        uiResult.setMsg("请求失败");
        return uiResult;
    }

    /**
     * 填充键值对到json中
     * @param param
     * @return
     */
    public static JSONObject fill(Map<Object, Object> param) {
        Set<Map.Entry<Object, Object>> entries = param.entrySet();
        JSONObject json = (JSONObject) JSONObject.toJSON(uiResult);
        for (Map.Entry entry : entries) {
            json.put((String) entry.getKey(), entry.getValue());
        }
        return json;
    }

    /**
     * 填充到返回结果
     * @param params
     * @return
     */
    public static JSONObject fill(Object ... params){
        JSONObject json = (JSONObject) JSONObject.toJSON(uiResult);
        for (int i = 0; i < params.length; i=i+2) {
            if(params[i] instanceof String){
                throw new IllegalArgumentException("添加到到返回结果中的单数入参类型必须为String类型");
            }
            json.put((String) params[i],params[i+1]);
        }
        return json;
    }

}
