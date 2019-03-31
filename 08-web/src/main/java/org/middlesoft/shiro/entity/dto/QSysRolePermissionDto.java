package org.middlesoft.shiro.entity.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysRolePermissionDto is a Querydsl query type for SysRolePermissionDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysRolePermissionDto extends EntityPathBase<SysRolePermissionDto> {

    private static final long serialVersionUID = -420739003L;

    public static final QSysRolePermissionDto sysRolePermissionDto = new QSysRolePermissionDto("sysRolePermissionDto");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final StringPath deleteStatus = createString("deleteStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> permissionId = createNumber("permissionId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public QSysRolePermissionDto(String variable) {
        super(SysRolePermissionDto.class, forVariable(variable));
    }

    public QSysRolePermissionDto(Path<? extends SysRolePermissionDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysRolePermissionDto(PathMetadata metadata) {
        super(SysRolePermissionDto.class, metadata);
    }

}

