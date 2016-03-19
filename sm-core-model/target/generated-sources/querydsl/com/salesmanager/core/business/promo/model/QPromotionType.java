package com.salesmanager.core.business.promo.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPromotionType is a Querydsl query type for PromotionType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPromotionType extends EntityPathBase<PromotionType> {

    private static final long serialVersionUID = 444571514L;

    public static final QPromotionType promotionType = new QPromotionType("promotionType");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public QPromotionType(String variable) {
        super(PromotionType.class, forVariable(variable));
    }

    public QPromotionType(Path<? extends PromotionType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotionType(PathMetadata<?> metadata) {
        super(PromotionType.class, metadata);
    }

}

