package com.salesmanager.core.business.system.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MerchantConfiguration.class)
public abstract class MerchantConfiguration_ {

	public static volatile SingularAttribute<MerchantConfiguration, MerchantConfigurationType> merchantConfigurationType;
	public static volatile SingularAttribute<MerchantConfiguration, Long> id;
	public static volatile SingularAttribute<MerchantConfiguration, MerchantStore> merchantStore;
	public static volatile SingularAttribute<MerchantConfiguration, AuditSection> auditSection;
	public static volatile SingularAttribute<MerchantConfiguration, String> value;
	public static volatile SingularAttribute<MerchantConfiguration, String> key;

}

