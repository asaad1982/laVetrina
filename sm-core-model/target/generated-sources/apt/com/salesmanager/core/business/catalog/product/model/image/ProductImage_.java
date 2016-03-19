package com.salesmanager.core.business.catalog.product.model.image;

import com.salesmanager.core.business.catalog.product.model.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductImage.class)
public abstract class ProductImage_ {

	public static volatile SingularAttribute<ProductImage, Product> product;
	public static volatile SingularAttribute<ProductImage, String> productImage;
	public static volatile SingularAttribute<ProductImage, Long> id;
	public static volatile SingularAttribute<ProductImage, Boolean> imageCrop;
	public static volatile ListAttribute<ProductImage, ProductImageDescription> descriptions;
	public static volatile SingularAttribute<ProductImage, Integer> imageType;
	public static volatile SingularAttribute<ProductImage, Boolean> defaultImage;

}

