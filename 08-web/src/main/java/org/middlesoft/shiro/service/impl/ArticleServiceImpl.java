package org.middlesoft.shiro.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.middlesoft.shiro.dao.ArticleRepository;
import org.middlesoft.shiro.entity.PageResult;
import org.middlesoft.shiro.entity.UiResult;
import org.middlesoft.shiro.entity.dto.ArticleDto;
import org.middlesoft.shiro.entity.dto.QArticleDto;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.middlesoft.shiro.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @link https://www.jianshu.com/p/69dcb1b85bbb
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * insert a item
     */
    @Override
    public UiResult add(ArticleQo item) {
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(item,articleDto);
        articleDto.setDeleteStatus(String.valueOf(1));
        ArticleDto save = articleRepository.save(articleDto);
        if(save != null){
            return UiResult.ok();
        }else{
            return UiResult.fail();
        }
    }

    /**
     * delete a item
     */
    @Override
    public long delete(ArticleQo item) {
        QArticleDto qa = QArticleDto.articleDto;
        long id = queryFactory.delete(qa).where(qa.id.eq(item.getId())).execute();
        log.debug("id:{}",id);
        return id;
    }

    /**
     * edit item
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long edit(ArticleQo item) {
        QArticleDto qa = QArticleDto.articleDto;
        long updateCount = queryFactory.update(qa).set(qa.content, item.getContent()).where(qa.id.eq(item.getId())).execute();
        return updateCount;
    }

    /**
     * find items
     */
    public List<ArticleDto> find(ArticleQo item) {
        return null;
    }

    public UiResult list(ArticleQo where) {
        QArticleDto qa = QArticleDto.articleDto;
        //写法一：
        JPAQuery<ArticleDto> query = queryFactory.selectFrom(qa).orderBy(qa.createTime.asc());
        //fetchCount 时，上面的orderBy不会执行
//        long total = query.fetchCount();
//        List<ArticleDto> list = query.offset(where.getPageNum()).limit(where.getPageRow()).fetch();
        //写法二：
        //执行了两条语句，一条是查询总数，一条是查询分页信息
        QueryResults<ArticleDto> queryResults = queryFactory.selectFrom(qa).orderBy(qa.createTime.asc()).offset(where.getPageNum()).limit(where.getPageRow()).fetchResults();
        log.debug("total:{}", queryResults.getTotal());
        log.debug("limit:{}", queryResults.getLimit());
        log.debug("offset:{}", queryResults.getOffset());
        log.debug("info:{}", queryResults.getResults());
        return PageResult.ok((int) (queryResults.getTotal() / where.getPageRow() + 1), (int) queryResults.getTotal(), queryResults.getResults());
    }
}
