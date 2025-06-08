package com.music.sale.adapter.persistence.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStoreEntity is a Querydsl query type for StoreEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreEntity extends EntityPathBase<StoreEntity> {

    private static final long serialVersionUID = -1822529115L;

    public static final QStoreEntity storeEntity = new QStoreEntity("storeEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    public final StringPath baseAddress = createString("baseAddress");

    public final StringPath businessNumber = createString("businessNumber");

    public final StringPath contactNumber = createString("contactNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath description = createString("description");

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> sellerId = createNumber("sellerId", Long.class);

    public final EnumPath<StoreEntity.StoreStatus> status = createEnum("status", StoreEntity.StoreStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath zipcode = createString("zipcode");

    public QStoreEntity(String variable) {
        super(StoreEntity.class, forVariable(variable));
    }

    public QStoreEntity(Path<? extends StoreEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoreEntity(PathMetadata metadata) {
        super(StoreEntity.class, metadata);
    }

}

