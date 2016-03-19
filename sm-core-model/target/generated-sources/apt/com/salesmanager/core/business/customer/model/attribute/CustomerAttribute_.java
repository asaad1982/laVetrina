package com.salesmanager.core.business.customer.model.attribute;

import com.salesmanager.core.business.customer.model.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerAttribute.class)
public abstract class CustomerAttribute_ {

	public static volatile SingularAttribute<CustomerAttribute, String> textValue;
	public static volatile SingularAttribute<CustomerAttribute, CustomerOptionValue> customerOptionValue;
	public static volatile SingularAttribute<CustomerAttribute, Long> id;
	public static volatile SingularAttribute<CustomerAttribute, CustomerOption> customerOption;
	public static volatile SingularAttribute<CustomerAttribute, Customer> customer;

}

