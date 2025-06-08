package com.music.sale.adapter.persistence.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthUserEntity is a Querydsl query type for AuthUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthUserEntity extends EntityPathBase<AuthUserEntity> {

    private static final long serialVersionUID = 153098301L;

    public static final QAuthUserEntity authUserEntity = new QAuthUserEntity("authUserEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAuthUserEntity(String variable) {
        super(AuthUserEntity.class, forVariable(variable));
    }

    public QAuthUserEntity(Path<? extends AuthUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthUserEntity(PathMetadata metadata) {
        super(AuthUserEntity.class, metadata);
    }

}

