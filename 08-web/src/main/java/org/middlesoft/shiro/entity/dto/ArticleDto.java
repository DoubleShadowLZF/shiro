package org.middlesoft.shiro.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the entity class of article
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "article")
public class ArticleDto {

    @Id
    @Column(insertable = false,updatable = false)
    private Long id;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Column(insertable = false,updatable = false)
    private java.sql.Timestamp createTime;

    /**
     * 更新时间
     */
    @Column(insertable = false,updatable = false)
    private java.sql.Timestamp updateTime;

    /**
     * 是否有效  1.有效  2无效
     */
    private String deleteStatus;

}
