package org.middlesoft.shiro.entity.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysUserDto is a Querydsl query type for SysUserDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysUserDto extends EntityPathBase<SysUserDto> {

    private static final long serialVersionUID = -1604218017L;

    public static final QSysUserDto sysUserDto = new QSysUserDto("sysUserDto");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath deleteStatus = createString("deleteStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public final StringPath username = createString("username");

    public QSysUserDto(String variable) {
        super(SysUserDto.class, forVariable(variable));
    }

    public QSysUserDto(Path<? extends SysUserDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysUserDto(PathMetadata metadata) {
        super(SysUserDto.class, metadata);
    }

}

