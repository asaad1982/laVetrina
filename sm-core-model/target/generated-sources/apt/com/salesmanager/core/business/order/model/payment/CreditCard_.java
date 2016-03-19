package com.salesmanager.core.business.order.model.payment;

import com.salesmanager.core.business.payments.model.CreditCardType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCard.class)
public abstract class CreditCard_ {

	public static volatile SingularAttribute<CreditCard, String> ccNumber;
	public static volatile SingularAttribute<CreditCard, String> ccCvv;
	public static volatile SingularAttribute<CreditCard, CreditCardType> cardType;
	public static volatile SingularAttribute<CreditCard, String> ccOwner;
	public static volatile SingularAttribute<CreditCard, String> ccExpires;

}

