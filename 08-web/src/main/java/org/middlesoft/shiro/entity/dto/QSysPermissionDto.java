package org.middlesoft.shiro.entity.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysPermissionDto is a Querydsl query type for SysPermissionDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysPermissionDto extends EntityPathBase<SysPermissionDto> {

    private static final long serialVersionUID = 840361627L;

    public static final QSysPermissionDto sysPermissionDto = new QSysPermissionDto("sysPermissionDto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath menuCode = createString("menuCode");

    public final StringPath menuName = createString("menuName");

    public final StringPath permissionCode = createString("permissionCode");

    public final StringPath permissionName = createString("permissionName");

    public final NumberPath<Long> requiredPermission = createNumber("requiredPermission", Long.class);

    public QSysPermissionDto(String variable) {
        super(SysPermissionDto.class, forVariable(variable));
    }

    public QSysPermissionDto(Path<? extends SysPermissionDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysPermissionDto(PathMetadata metadata) {
        super(SysPermissionDto.class, metadata);
    }

}

