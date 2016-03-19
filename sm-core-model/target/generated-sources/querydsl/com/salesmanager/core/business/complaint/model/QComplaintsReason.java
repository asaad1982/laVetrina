package com.salesmanager.core.business.complaint.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QComplaintsReason is a Querydsl query type for ComplaintsReason
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QComplaintsReason extends EntityPathBase<ComplaintsReason> {

    private static final long serialVersionUID = 429811923L;

    public static final QComplaintsReason complaintsReason = new QComplaintsReason("complaintsReason");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath arabicName = createString("arabicName");

    public final StringPath englishName = createString("englishName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public QComplaintsReason(String variable) {
        super(ComplaintsReason.class, forVariable(variable));
    }

    public QComplaintsReason(Path<? extends ComplaintsReason> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintsReason(PathMetadata<?> metadata) {
        super(ComplaintsReason.class, metadata);
    }

}

