package com.salesmanager.core.business.catalog.product.model.manufacturer;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ManufacturerDescription.class)
public abstract class ManufacturerDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<ManufacturerDescription, Integer> urlClicked;
	public static volatile SingularAttribute<ManufacturerDescription, Date> dateLastClick;
	public static volatile SingularAttribute<ManufacturerDescription, String> url;
	public static volatile SingularAttribute<ManufacturerDescription, Manufacturer> manufacturer;

}

