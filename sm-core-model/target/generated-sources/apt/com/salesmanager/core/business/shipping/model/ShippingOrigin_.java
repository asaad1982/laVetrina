package com.salesmanager.core.business.shipping.model;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.zone.model.Zone;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingOrigin.class)
public abstract class ShippingOrigin_ {

	public static volatile SingularAttribute<ShippingOrigin, Country> country;
	public static volatile SingularAttribute<ShippingOrigin, String> address;
	public static volatile SingularAttribute<ShippingOrigin, String> city;
	public static volatile SingularAttribute<ShippingOrigin, Zone> zone;
	public static volatile SingularAttribute<ShippingOrigin, String> postalCode;
	public static volatile SingularAttribute<ShippingOrigin, Boolean> active;
	public static volatile SingularAttribute<ShippingOrigin, Long> id;
	public static volatile SingularAttribute<ShippingOrigin, String> state;
	public static volatile SingularAttribute<ShippingOrigin, MerchantStore> merchantStore;

}

