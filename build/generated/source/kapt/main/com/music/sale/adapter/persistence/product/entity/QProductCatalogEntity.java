package com.music.sale.adapter.persistence.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductCatalogEntity is a Querydsl query type for ProductCatalogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductCatalogEntity extends EntityPathBase<ProductCatalogEntity> {

    private static final long serialVersionUID = -1359312102L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductCatalogEntity productCatalogEntity = new QProductCatalogEntity("productCatalogEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    public final MapPath<String, Object, SimplePath<Object>> attributes = this.<String, Object, SimplePath<Object>>createMap("attributes", String.class, Object.class, SimplePath.class);

    public final com.music.sale.adapter.persistence.category.entity.QCategoryEntity category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QProductCatalogEntity(String variable) {
        this(ProductCatalogEntity.class, forVariable(variable), INITS);
    }

    public QProductCatalogEntity(Path<? extends ProductCatalogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductCatalogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductCatalogEntity(PathMetadata metadata, PathInits inits) {
        this(ProductCatalogEntity.class, metadata, inits);
    }

    public QProductCatalogEntity(Class<? extends ProductCatalogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.music.sale.adapter.persistence.category.entity.QCategoryEntity(forProperty("category"), inits.get("category")) : null;
    }

}

