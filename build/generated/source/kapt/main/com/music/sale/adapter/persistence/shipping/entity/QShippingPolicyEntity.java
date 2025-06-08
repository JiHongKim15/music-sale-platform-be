package com.music.sale.adapter.persistence.shipping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingPolicyEntity is a Querydsl query type for ShippingPolicyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingPolicyEntity extends EntityPathBase<ShippingPolicyEntity> {

    private static final long serialVersionUID = -2134725619L;

    public static final QShippingPolicyEntity shippingPolicyEntity = new QShippingPolicyEntity("shippingPolicyEntity");

    public final com.music.sale.adapter.persistence.common.QBaseEntity _super = new com.music.sale.adapter.persistence.common.QBaseEntity(this);

    public final BooleanPath canPickup = createBoolean("canPickup");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<java.math.BigDecimal> freeShippingThreshold = createNumber("freeShippingThreshold", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath internationalShipping = createBoolean("internationalShipping");

    public final BooleanPath isDefault = createBoolean("isDefault");

    public final NumberPath<Integer> maxDeliveryDays = createNumber("maxDeliveryDays", Integer.class);

    public final NumberPath<Integer> minDeliveryDays = createNumber("minDeliveryDays", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath restrictedAreas = createString("restrictedAreas");

    public final NumberPath<Long> sellerId = createNumber("sellerId", Long.class);

    public final NumberPath<java.math.BigDecimal> shippingFee = createNumber("shippingFee", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QShippingPolicyEntity(String variable) {
        super(ShippingPolicyEntity.class, forVariable(variable));
    }

    public QShippingPolicyEntity(Path<? extends ShippingPolicyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingPolicyEntity(PathMetadata metadata) {
        super(ShippingPolicyEntity.class, metadata);
    }

}

