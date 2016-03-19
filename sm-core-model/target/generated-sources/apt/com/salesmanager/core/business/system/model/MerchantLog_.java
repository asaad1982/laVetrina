package com.salesmanager.core.business.system.model;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MerchantLog.class)
public abstract class MerchantLog_ {

	public static volatile SingularAttribute<MerchantLog, String> log;
	public static volatile SingularAttribute<MerchantLog, String> module;
	public static volatile SingularAttribute<MerchantLog, Long> id;
	public static volatile SingularAttribute<MerchantLog, MerchantStore> store;

}

