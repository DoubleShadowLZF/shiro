package org.middlesoft.shiro.entity.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleDto is a Querydsl query type for ArticleDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleDto extends EntityPathBase<ArticleDto> {

    private static final long serialVersionUID = -733637151L;

    public static final QArticleDto articleDto = new QArticleDto("articleDto");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath deleteStatus = createString("deleteStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public QArticleDto(String variable) {
        super(ArticleDto.class, forVariable(variable));
    }

    public QArticleDto(Path<? extends ArticleDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleDto(PathMetadata metadata) {
        super(ArticleDto.class, metadata);
    }

}

