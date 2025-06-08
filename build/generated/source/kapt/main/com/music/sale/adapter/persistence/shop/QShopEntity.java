package com.music.sale.adapter.persistence.shop;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopEntity is a Querydsl query type for ShopEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopEntity extends EntityPathBase<ShopEntity> {

    private static final long serialVersionUID = 827487410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopEntity shopEntity = new QShopEntity("shopEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath businessHours = createString("businessHours");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isShippingAvailable = createBoolean("isShippingAvailable");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final com.music.sale.adapter.persistence.user.entity.QUserEntity seller;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QShopEntity(String variable) {
        this(ShopEntity.class, forVariable(variable), INITS);
    }

    public QShopEntity(Path<? extends ShopEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopEntity(PathMetadata metadata, PathInits inits) {
        this(ShopEntity.class, metadata, inits);
    }

    public QShopEntity(Class<? extends ShopEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seller = inits.isInitialized("seller") ? new com.music.sale.adapter.persistence.user.entity.QUserEntity(forProperty("seller")) : null;
    }

}

