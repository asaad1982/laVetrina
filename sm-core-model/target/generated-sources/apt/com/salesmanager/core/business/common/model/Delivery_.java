package com.salesmanager.core.business.common.model;

import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.zone.model.Zone;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Delivery.class)
public abstract class Delivery_ {

	public static volatile SingularAttribute<Delivery, String> lastName;
	public static volatile SingularAttribute<Delivery, String> firstName;
	public static volatile SingularAttribute<Delivery, Country> country;
	public static volatile SingularAttribute<Delivery, String> address;
	public static volatile SingularAttribute<Delivery, String> city;
	public static volatile SingularAttribute<Delivery, Zone> zone;
	public static volatile SingularAttribute<Delivery, String> postalCode;
	public static volatile SingularAttribute<Delivery, String> company;
	public static volatile SingularAttribute<Delivery, String> telephone;
	public static volatile SingularAttribute<Delivery, String> state;

}

