package org.middlesoft.shiro.entity.qo;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * the entity class of article
 */
@Data
@Accessors(chain = true)
public class ArticleQo extends PageQo{

    private Long id;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    private java.sql.Timestamp createTime;

    /**
     * 更新时间
     */
    private java.sql.Timestamp updateTime;

    /**
     * 是否有效  1.有效  2无效
     */
    private String deleteStatus;

}
