package org.middlesoft.shiro;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.dto.ArticleDto;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.middlesoft.shiro.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ArticleTest {

    @Autowired
    ArticleService articleService;

    private Long id ;
    @Test
    public void list(){
        ArticleQo where = new ArticleQo();
        UiResult list = articleService.list(where);
        log.info("文章列表查询：{}", JSONObject.toJSON(list));
    }

    @Test
    public void add(){
        ArticleQo where = new ArticleQo();
        where.setContent("Hello world");
        UiResult add = articleService.add(where);
        if(add.getInfo() != null){
            id = (Long) add.getInfo();
        }
        log.info("{}",add);
    }

    @Test
    public void edit(){
        ArticleQo where = new ArticleQo();
        where.setId(id).setContent("1 + 1 = 2");
        long edit = articleService.edit(where);
        log.info("{}",edit);
        List<ArticleDto> articleDtos = articleService.find(where);
        log.info("{}",articleDtos);
    }

    @Test
    public void delete(){
        ArticleQo where = new ArticleQo();
        where.setId(id);
        long delete = articleService.delete(where);
        log.info("{}",delete);
    }
}
