package com.salesmanager.core.business.promo.model;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.reference.country.model.Country;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionRule.class)
public abstract class PromotionRule_ {

	public static volatile ListAttribute<PromotionRule, Manufacturer> brands;
	public static volatile SingularAttribute<PromotionRule, PromotionTragetAge> promotionTragetAge;
	public static volatile SingularAttribute<PromotionRule, String> targetGender;
	public static volatile SingularAttribute<PromotionRule, Long> id;
	public static volatile ListAttribute<PromotionRule, Customer> customers;
	public static volatile ListAttribute<PromotionRule, Country> countries;
	public static volatile ListAttribute<PromotionRule, Category> categories;
	public static volatile ListAttribute<PromotionRule, Product> products;

}

