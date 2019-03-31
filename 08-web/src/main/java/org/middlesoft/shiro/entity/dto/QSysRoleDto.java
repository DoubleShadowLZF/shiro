package org.middlesoft.shiro.entity.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysRoleDto is a Querydsl query type for SysRoleDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysRoleDto extends EntityPathBase<SysRoleDto> {

    private static final long serialVersionUID = -80201004L;

    public static final QSysRoleDto sysRoleDto = new QSysRoleDto("sysRoleDto");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath deleteStatus = createString("deleteStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roleName = createString("roleName");

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public QSysRoleDto(String variable) {
        super(SysRoleDto.class, forVariable(variable));
    }

    public QSysRoleDto(Path<? extends SysRoleDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysRoleDto(PathMetadata metadata) {
        super(SysRoleDto.class, metadata);
    }

}

