package com.salesmanager.core.business.promo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionDescription.class)
public abstract class PromotionDescription_ {

	public static volatile SingularAttribute<PromotionDescription, Integer> languageId;
	public static volatile SingularAttribute<PromotionDescription, String> name;
	public static volatile SingularAttribute<PromotionDescription, String> description;
	public static volatile SingularAttribute<PromotionDescription, Long> id;
	public static volatile SingularAttribute<PromotionDescription, Promotion> promotion;

}

