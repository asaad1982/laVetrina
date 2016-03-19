package com.salesmanager.core.business.content.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Content.class)
public abstract class Content_ {

	public static volatile SingularAttribute<Content, String> code;
	public static volatile SingularAttribute<Content, Boolean> visible;
	public static volatile SingularAttribute<Content, Integer> sortOrder;
	public static volatile SingularAttribute<Content, ContentPosition> contentPosition;
	public static volatile SingularAttribute<Content, Long> id;
	public static volatile SingularAttribute<Content, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Content, AuditSection> auditSection;
	public static volatile ListAttribute<Content, ContentDescription> descriptions;
	public static volatile SingularAttribute<Content, ContentType> contentType;

}

