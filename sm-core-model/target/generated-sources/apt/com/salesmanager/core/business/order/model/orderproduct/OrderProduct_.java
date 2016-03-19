package com.salesmanager.core.business.order.model.orderproduct;

import com.salesmanager.core.business.order.model.Order;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProduct.class)
public abstract class OrderProduct_ {

	public static volatile SingularAttribute<OrderProduct, Integer> productQuantity;
	public static volatile SetAttribute<OrderProduct, OrderProductDownload> downloads;
	public static volatile SetAttribute<OrderProduct, OrderProductAttribute> orderAttributes;
	public static volatile SingularAttribute<OrderProduct, Long> id;
	public static volatile SingularAttribute<OrderProduct, String> sku;
	public static volatile SingularAttribute<OrderProduct, BigDecimal> oneTimeCharge;
	public static volatile SetAttribute<OrderProduct, OrderProductPrice> prices;
	public static volatile SingularAttribute<OrderProduct, String> productName;
	public static volatile SingularAttribute<OrderProduct, Order> order;

}

