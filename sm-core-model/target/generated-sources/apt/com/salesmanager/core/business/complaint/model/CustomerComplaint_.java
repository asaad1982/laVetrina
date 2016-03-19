package com.salesmanager.core.business.complaint.model;

import com.salesmanager.core.business.customer.model.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerComplaint.class)
public abstract class CustomerComplaint_ {

	public static volatile SingularAttribute<CustomerComplaint, String> notes;
	public static volatile SingularAttribute<CustomerComplaint, ComplaintsReason> customerComplaintReason;
	public static volatile SingularAttribute<CustomerComplaint, Date> complaintsDate;
	public static volatile SingularAttribute<CustomerComplaint, String> closingReason;
	public static volatile SingularAttribute<CustomerComplaint, Long> id;
	public static volatile SingularAttribute<CustomerComplaint, Date> closingDate;
	public static volatile SingularAttribute<CustomerComplaint, Customer> customer;
	public static volatile SingularAttribute<CustomerComplaint, String> status;

}

