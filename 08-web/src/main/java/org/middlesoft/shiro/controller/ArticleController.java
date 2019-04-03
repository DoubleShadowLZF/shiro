package org.middlesoft.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.middlesoft.shiro.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章管理路由层
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表
     * @param query
     * @return
     */
    @RequiresPermissions("article:list")
    @GetMapping("listArticle")
    public UiResult listArticle(ArticleQo query){
        return articleService.list(query);
    }

    /**
     * 新增文章
     * @param articleQo
     * @return
     */
    @RequiresPermissions("article:add")
    @PostMapping("/addArticle")
    public Object aaArticle(@RequestBody ArticleQo articleQo){
        return articleService.add(articleQo);
    }

    /**
     * 修改文章
     * @param where
     * @return
     */
    @RequiresPermissions("article:update")
    @PostMapping("updateArticle")
    public Object updateArticle(@RequestBody ArticleQo where){
        long edit = articleService.edit(where);
        return UiResult.ok(edit);
    }

}
