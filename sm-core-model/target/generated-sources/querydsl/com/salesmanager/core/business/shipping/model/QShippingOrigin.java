package com.salesmanager.core.business.shipping.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShippingOrigin is a Querydsl query type for ShippingOrigin
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShippingOrigin extends EntityPathBase<ShippingOrigin> {

    private static final long serialVersionUID = -429560262L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingOrigin shippingOrigin = new QShippingOrigin("shippingOrigin");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath address = createString("address");

    public final StringPath city = createString("city");

    public final com.salesmanager.core.business.reference.country.model.QCountry country;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath postalCode = createString("postalCode");

    public final StringPath state = createString("state");

    public final com.salesmanager.core.business.reference.zone.model.QZone zone;

    public QShippingOrigin(String variable) {
        this(ShippingOrigin.class, forVariable(variable), INITS);
    }

    public QShippingOrigin(Path<? extends ShippingOrigin> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShippingOrigin(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShippingOrigin(PathMetadata<?> metadata, PathInits inits) {
        this(ShippingOrigin.class, metadata, inits);
    }

    public QShippingOrigin(Class<? extends ShippingOrigin> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new com.salesmanager.core.business.reference.country.model.QCountry(forProperty("country"), inits.get("country")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
        this.zone = inits.isInitialized("zone") ? new com.salesmanager.core.business.reference.zone.model.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

