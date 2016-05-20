package com.salesmanager.core.business.reference.country.model;

import com.salesmanager.core.business.reference.geozone.model.GeoZone;
import com.salesmanager.core.business.reference.zone.model.Zone;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Country.class)
public abstract class Country_ {

	public static volatile SingularAttribute<Country, String> isoCode;
	public static volatile SingularAttribute<Country, Integer> id;
	public static volatile ListAttribute<Country, City> citys;
	public static volatile ListAttribute<Country, Zone> zones;
	public static volatile ListAttribute<Country, CountryDescription> descriptions;
	public static volatile SingularAttribute<Country, GeoZone> geoZone;
	public static volatile SingularAttribute<Country, Boolean> supported;

}

