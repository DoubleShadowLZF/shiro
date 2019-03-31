package org.middlesoft.shiro.filter;


import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.middlesoft.shiro.entity.ErrorEnum;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UserFilter extends FormAuthenticationFilter {
    @Override
protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        //此处需要使用JSONObject对象
        JSONObject json = new JSONObject();
        json.put("code", ErrorEnum.E_4011.getErrorCode());
        json.put("msg",ErrorEnum.E_4011.getErrorMessage());

        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = res.getWriter();
            out.println(json);
        }catch (Exception e){

        }finally {
            if( out != null){
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(UserFilter filter){
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

}
