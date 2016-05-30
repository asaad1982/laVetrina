package com.salesmanager.core.business.promo.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Promotion.class)
public abstract class Promotion_ {

	public static volatile SingularAttribute<Promotion, Date> endate;
	public static volatile SingularAttribute<Promotion, PromotionRule> promotionRule;
	public static volatile SingularAttribute<Promotion, PromotionType> promotionType;
	public static volatile SingularAttribute<Promotion, Double> promotionVal;
	public static volatile SingularAttribute<Promotion, Long> id;
	public static volatile ListAttribute<Promotion, PromotionDescription> promotionDescriptions;
	public static volatile SingularAttribute<Promotion, Date> startDate;
	public static volatile SingularAttribute<Promotion, String> status;

}

