package com.music.sale.adapter.persistence.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingEmbeddable is a Querydsl query type for ShippingEmbeddable
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingEmbeddable extends BeanPath<ShippingEmbeddable> {

    private static final long serialVersionUID = 1191217298L;

    public static final QShippingEmbeddable shippingEmbeddable = new QShippingEmbeddable("shippingEmbeddable");

    public final StringPath address = createString("address");

    public final StringPath method = createString("method");

    public final StringPath trackingNumber = createString("trackingNumber");

    public QShippingEmbeddable(String variable) {
        super(ShippingEmbeddable.class, forVariable(variable));
    }

    public QShippingEmbeddable(Path<? extends ShippingEmbeddable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingEmbeddable(PathMetadata metadata) {
        super(ShippingEmbeddable.class, metadata);
    }

}

