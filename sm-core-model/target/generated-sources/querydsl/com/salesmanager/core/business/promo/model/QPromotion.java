package com.salesmanager.core.business.promo.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPromotion is a Querydsl query type for Promotion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPromotion extends EntityPathBase<Promotion> {

    private static final long serialVersionUID = -1781879776L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromotion promotion = new QPromotion("promotion");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    

    public final DateTimePath<java.util.Date> endate = createDateTime("endate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final ListPath<PromotionDescription, QPromotionDescription> promotionDescriptions = this.<PromotionDescription, QPromotionDescription>createList("promotionDescriptions", PromotionDescription.class, QPromotionDescription.class, PathInits.DIRECT2);

    public final QPromotionRule promotionRule;

    public final QPromotionType promotionType;

    public final NumberPath<Double> promotionVal = createNumber("promotionVal", Double.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public QPromotion(String variable) {
        this(Promotion.class, forVariable(variable), INITS);
    }

    public QPromotion(Path<? extends Promotion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPromotion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPromotion(PathMetadata<?> metadata, PathInits inits) {
        this(Promotion.class, metadata, inits);
    }

    public QPromotion(Class<? extends Promotion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        
        this.promotionRule = inits.isInitialized("promotionRule") ? new QPromotionRule(forProperty("promotionRule")) : null;
        this.promotionType = inits.isInitialized("promotionType") ? new QPromotionType(forProperty("promotionType")) : null;
    }

}

