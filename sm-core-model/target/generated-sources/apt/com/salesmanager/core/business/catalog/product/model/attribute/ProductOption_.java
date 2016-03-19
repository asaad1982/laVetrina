package com.salesmanager.core.business.catalog.product.model.attribute;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductOption.class)
public abstract class ProductOption_ {

	public static volatile SingularAttribute<ProductOption, String> code;
	public static volatile SingularAttribute<ProductOption, Integer> productOptionSortOrder;
	public static volatile SingularAttribute<ProductOption, Boolean> readOnly;
	public static volatile SingularAttribute<ProductOption, Long> id;
	public static volatile SingularAttribute<ProductOption, String> productOptionType;
	public static volatile SingularAttribute<ProductOption, MerchantStore> merchantStore;
	public static volatile SetAttribute<ProductOption, ProductOptionDescription> descriptions;

}

