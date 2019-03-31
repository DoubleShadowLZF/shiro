package org.middlesoft.shiro.service;

import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.middlesoft.shiro.entity.dto.ArticleDto;

import java.util.List;

/**
 * 
 */
public interface ArticleService {
//Define something special
    /**
     * insert a item 
     * 
    */
    UiResult add(ArticleQo item);


    /**
     * delete a item 
     * 
    */
    long delete(ArticleQo item);


    /**
     * edit item 
     * 
    */
    long edit(ArticleQo item);


    /**
     * find items
     * 
    */
    List<ArticleDto> find(ArticleQo item);


    UiResult list(ArticleQo query);
}
