package com.music.sale.adapter.persistence.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductItemEntity is a Querydsl query type for ProductItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductItemEntity extends EntityPathBase<ProductItemEntity> {

    private static final long serialVersionUID = -2140706856L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductItemEntity productItemEntity = new QProductItemEntity("productItemEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    public final QProductCatalogEntity catalog;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final MapPath<String, Object, SimplePath<Object>> customAttributes = this.<String, Object, SimplePath<Object>>createMap("customAttributes", String.class, Object.class, SimplePath.class);

    public final StringPath customName = createString("customName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.music.sale.adapter.persistence.seller.entity.QSellerEntity seller;

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public final com.music.sale.adapter.persistence.store.entity.QStoreEntity store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QProductItemEntity(String variable) {
        this(ProductItemEntity.class, forVariable(variable), INITS);
    }

    public QProductItemEntity(Path<? extends ProductItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductItemEntity(PathMetadata metadata, PathInits inits) {
        this(ProductItemEntity.class, metadata, inits);
    }

    public QProductItemEntity(Class<? extends ProductItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.catalog = inits.isInitialized("catalog") ? new QProductCatalogEntity(forProperty("catalog"), inits.get("catalog")) : null;
        this.seller = inits.isInitialized("seller") ? new com.music.sale.adapter.persistence.seller.entity.QSellerEntity(forProperty("seller"), inits.get("seller")) : null;
        this.store = inits.isInitialized("store") ? new com.music.sale.adapter.persistence.store.entity.QStoreEntity(forProperty("store")) : null;
    }

}

