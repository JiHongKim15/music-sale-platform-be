package com.music.sale.adapter.persistence.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentEmbeddable is a Querydsl query type for PaymentEmbeddable
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPaymentEmbeddable extends BeanPath<PaymentEmbeddable> {

    private static final long serialVersionUID = -1803754612L;

    public static final QPaymentEmbeddable paymentEmbeddable = new QPaymentEmbeddable("paymentEmbeddable");

    public final StringPath method = createString("method");

    public final StringPath status = createString("status");

    public QPaymentEmbeddable(String variable) {
        super(PaymentEmbeddable.class, forVariable(variable));
    }

    public QPaymentEmbeddable(Path<? extends PaymentEmbeddable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentEmbeddable(PathMetadata metadata) {
        super(PaymentEmbeddable.class, metadata);
    }

}

