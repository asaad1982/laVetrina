package com.salesmanager.core.business.complaint.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerComplaint is a Querydsl query type for CustomerComplaint
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerComplaint extends EntityPathBase<CustomerComplaint> {

    private static final long serialVersionUID = 1386721286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerComplaint customerComplaint = new QCustomerComplaint("customerComplaint");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final DateTimePath<java.util.Date> closingDate = createDateTime("closingDate", java.util.Date.class);

    public final StringPath closingReason = createString("closingReason");

    public final DateTimePath<java.util.Date> complaintsDate = createDateTime("complaintsDate", java.util.Date.class);

    public final com.salesmanager.core.business.customer.model.QCustomer customer;

    public final QComplaintsReason customerComplaintReason;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath notes = createString("notes");

    public final StringPath status = createString("status");

    public QCustomerComplaint(String variable) {
        this(CustomerComplaint.class, forVariable(variable), INITS);
    }

    public QCustomerComplaint(Path<? extends CustomerComplaint> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerComplaint(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerComplaint(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerComplaint.class, metadata, inits);
    }

    public QCustomerComplaint(Class<? extends CustomerComplaint> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new com.salesmanager.core.business.customer.model.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.customerComplaintReason = inits.isInitialized("customerComplaintReason") ? new QComplaintsReason(forProperty("customerComplaintReason")) : null;
    }

}

