package org.middlesoft.shiro;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestTest {

    @Test
    public void login(){
        String url = "http://localhost:8080/login/auth";
        RestTemplate rest = new RestTemplate();
        JSONObject json = new JSONObject();
        json.put("username","admin");
        json.put("password","123456");
        Object response = rest.postForObject(url, json, Object.class);
        log.info("response:{}",response);
    }

    @Test
    public void getInfo(){
        String url = "http://localhost:8080/login/getInfo";
        RestTemplate rest = new RestTemplate();
//        JSONObject json = new JSONObject();
//        json.put("username","admin");
//        json.put("password","123456");
        String result = rest.postForObject(url,null,String.class);
        log.info(result);
    }

    @Test
    public void articleAdd(){
        String url = "http://localhost:8081/article";
        RestTemplate rest = new RestTemplate();
        ArticleQo where = new ArticleQo();
        where.setContent("hello world");
        String result = rest.postForObject(url, where, String.class);
        log.info(result);
    }
}
