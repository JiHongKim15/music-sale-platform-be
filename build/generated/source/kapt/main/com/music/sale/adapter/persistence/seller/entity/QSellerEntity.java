package com.music.sale.adapter.persistence.seller.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSellerEntity is a Querydsl query type for SellerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSellerEntity extends EntityPathBase<SellerEntity> {

    private static final long serialVersionUID = 1086744797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSellerEntity sellerEntity = new QSellerEntity("sellerEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    public final StringPath businessNumber = createString("businessNumber");

    public final StringPath companyName = createString("companyName");

    public final StringPath contactEmail = createString("contactEmail");

    public final StringPath contactPhone = createString("contactPhone");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<SellerEntity.SellerStatus> status = createEnum("status", SellerEntity.SellerStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final com.music.sale.adapter.persistence.user.entity.QUserEntity user;

    public QSellerEntity(String variable) {
        this(SellerEntity.class, forVariable(variable), INITS);
    }

    public QSellerEntity(Path<? extends SellerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSellerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSellerEntity(PathMetadata metadata, PathInits inits) {
        this(SellerEntity.class, metadata, inits);
    }

    public QSellerEntity(Class<? extends SellerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.music.sale.adapter.persistence.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

