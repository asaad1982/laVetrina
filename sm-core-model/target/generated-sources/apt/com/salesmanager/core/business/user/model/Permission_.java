package com.salesmanager.core.business.user.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile ListAttribute<Permission, Group> groups;
	public static volatile SingularAttribute<Permission, Integer> id;
	public static volatile SingularAttribute<Permission, AuditSection> auditSection;
	public static volatile SingularAttribute<Permission, String> permissionName;

}

