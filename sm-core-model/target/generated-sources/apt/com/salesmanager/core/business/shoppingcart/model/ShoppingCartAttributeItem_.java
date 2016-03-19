package com.salesmanager.core.business.shoppingcart.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShoppingCartAttributeItem.class)
public abstract class ShoppingCartAttributeItem_ {

	public static volatile SingularAttribute<ShoppingCartAttributeItem, ShoppingCartItem> shoppingCartItem;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, Long> id;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, AuditSection> auditSection;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, Long> productAttributeId;

}

