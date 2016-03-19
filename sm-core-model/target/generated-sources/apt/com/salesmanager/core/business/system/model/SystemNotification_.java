package com.salesmanager.core.business.system.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.user.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemNotification.class)
public abstract class SystemNotification_ {

	public static volatile SingularAttribute<SystemNotification, Date> endDate;
	public static volatile SingularAttribute<SystemNotification, Long> id;
	public static volatile SingularAttribute<SystemNotification, MerchantStore> merchantStore;
	public static volatile SingularAttribute<SystemNotification, String> value;
	public static volatile SingularAttribute<SystemNotification, User> user;
	public static volatile SingularAttribute<SystemNotification, AuditSection> auditSection;
	public static volatile SingularAttribute<SystemNotification, String> key;
	public static volatile SingularAttribute<SystemNotification, Date> startDate;

}

