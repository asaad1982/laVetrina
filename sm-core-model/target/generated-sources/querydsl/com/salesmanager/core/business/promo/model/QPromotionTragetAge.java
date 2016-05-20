package com.salesmanager.core.business.promo.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPromotionTragetAge is a Querydsl query type for PromotionTragetAge
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPromotionTragetAge extends EntityPathBase<PromotionTragetAge> {

    private static final long serialVersionUID = -668730132L;

    public static final QPromotionTragetAge promotionTragetAge = new QPromotionTragetAge("promotionTragetAge");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> maxVal = createNumber("maxVal", Integer.class);

    public final NumberPath<Integer> minVal = createNumber("minVal", Integer.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public QPromotionTragetAge(String variable) {
        super(PromotionTragetAge.class, forVariable(variable));
    }

    public QPromotionTragetAge(Path<? extends PromotionTragetAge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotionTragetAge(PathMetadata<?> metadata) {
        super(PromotionTragetAge.class, metadata);
    }

}

