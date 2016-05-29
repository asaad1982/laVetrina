package com.salesmanager.core.business.catalog.product.model.manufacturer;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Manufacturer.class)
public abstract class Manufacturer_ {

	public static volatile SingularAttribute<Manufacturer, String> image;
	public static volatile SingularAttribute<Manufacturer, String> code;
	public static volatile SingularAttribute<Manufacturer, Boolean> available;
	public static volatile SingularAttribute<Manufacturer, Long> id;
	public static volatile SingularAttribute<Manufacturer, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Manufacturer, AuditSection> auditSection;
	public static volatile SetAttribute<Manufacturer, ManufacturerDescription> descriptions;
	public static volatile SingularAttribute<Manufacturer, Integer> order;

}

