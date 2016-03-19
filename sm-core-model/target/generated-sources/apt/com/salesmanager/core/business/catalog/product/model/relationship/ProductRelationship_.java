package com.salesmanager.core.business.catalog.product.model.relationship;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductRelationship.class)
public abstract class ProductRelationship_ {

	public static volatile SingularAttribute<ProductRelationship, Product> product;
	public static volatile SingularAttribute<ProductRelationship, String> code;
	public static volatile SingularAttribute<ProductRelationship, Product> relatedProduct;
	public static volatile SingularAttribute<ProductRelationship, Boolean> active;
	public static volatile SingularAttribute<ProductRelationship, Long> id;
	public static volatile SingularAttribute<ProductRelationship, MerchantStore> store;

}

