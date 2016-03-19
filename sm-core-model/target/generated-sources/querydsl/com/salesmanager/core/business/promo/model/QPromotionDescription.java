package com.salesmanager.core.business.promo.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPromotionDescription is a Querydsl query type for PromotionDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPromotionDescription extends EntityPathBase<PromotionDescription> {

    private static final long serialVersionUID = 367373244L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromotionDescription promotionDescription = new QPromotionDescription("promotionDescription");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> languageId = createNumber("languageId", Integer.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QPromotion promotion;

    public QPromotionDescription(String variable) {
        this(PromotionDescription.class, forVariable(variable), INITS);
    }

    public QPromotionDescription(Path<? extends PromotionDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPromotionDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPromotionDescription(PathMetadata<?> metadata, PathInits inits) {
        this(PromotionDescription.class, metadata, inits);
    }

    public QPromotionDescription(Class<? extends PromotionDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.promotion = inits.isInitialized("promotion") ? new QPromotion(forProperty("promotion"), inits.get("promotion")) : null;
    }

}

