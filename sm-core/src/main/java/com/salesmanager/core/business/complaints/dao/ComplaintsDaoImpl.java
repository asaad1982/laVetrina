package com.salesmanager.core.business.complaints.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.complaint.model.QComplaintsReason;
import com.salesmanager.core.business.complaint.model.QCustomerComplaint;
import com.salesmanager.core.business.customer.model.QCustomer;
import com.salesmanager.core.business.customer.model.attribute.QCustomerAttribute;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOption;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.language.model.Language;
@Repository("complaintsDAO")
public class ComplaintsDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerComplaint>
		implements ComplaintsDao {

	@Override
	public List<CustomerComplaint> listComplaintsReason() {
		QCustomerComplaint qCustomerComplaint = QCustomerComplaint.customerComplaint;
		QCustomer qCustomer=QCustomer.customer;
		QComplaintsReason qComplaintsReason=QComplaintsReason.complaintsReason;
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerComplaint)
		.innerJoin(qCustomerComplaint.customer, qCustomer).fetch()
		//.innerJoin(qCustomerComplaint.qComplaintsReason, qComplaintsReason).fetch()
			.where(qCustomerComplaint.status.ne("solved"))
			.orderBy(qCustomerComplaint.id.asc());
		
		return query.distinct().list(qCustomerComplaint);
	}

	@Override
	public List<CustomerComplaint> getByName(String categoryName,String status
			) {
		QCustomerComplaint qCustomerComplaint = QCustomerComplaint.customerComplaint;
		QCustomer qCustomer=QCustomer.customer;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		BooleanExpression predicate=null;
		if(!StringUtils.isBlank(categoryName)){
			predicate=qCustomerComplaint.notes.like("%"+categoryName+"%");
		}if(!StringUtils.isBlank(status)){
			if(predicate!=null)
			predicate.and(qCustomerComplaint.status.eq(status));
			else
				predicate=	qCustomerComplaint.status.eq(status);
		}
		query.from(qCustomerComplaint).
		innerJoin(qCustomerComplaint.customer, qCustomer).fetch()
		//.innerJoin(qCustomerComplaint.qComplaintsReason, qComplaintsReason).fetch()
		
			.where(predicate)
			.orderBy(qCustomerComplaint.id.asc());
		
		return query.distinct().list(qCustomerComplaint);
	}
	

	@Override
	public List<CustomerComplaint> getByName(String note, String status,
			String customerMail, String reason, String date) {
		QCustomerComplaint qCustomerComplaint = QCustomerComplaint.customerComplaint;
		QCustomer qCustomer=QCustomer.customer;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		BooleanExpression predicate=null;
		if(!StringUtils.isBlank(note)){
			predicate=qCustomerComplaint.notes.like("%"+note+"%");
		}if(!StringUtils.isBlank(status)){
			if(predicate!=null)
			predicate.and(qCustomerComplaint.status.eq(status));
			else
				predicate=	qCustomerComplaint.status.eq(status);
		}if(!StringUtils.isBlank(customerMail)){
			if(predicate!=null)
			predicate.and(qCustomerComplaint.customer.emailAddress.like("%"+customerMail+"%"));
			else
				predicate=	qCustomerComplaint.customer.emailAddress.like("%"+customerMail+"%");
		}if(!StringUtils.isBlank(reason)){
			if(predicate!=null)
			predicate.and(qCustomerComplaint.customerComplaintReason.englishName.like("%"+reason+"%"));
			else
				predicate=	qCustomerComplaint.customerComplaintReason.englishName.like("%"+reason+"%");
		}if(!StringUtils.isBlank(date)){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date compDate=new Date();
			try {
				compDate = formatter.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(predicate!=null)
			predicate.and(qCustomerComplaint.complaintsDate.between(compDate, new Date()));
			else
				predicate=	qCustomerComplaint.complaintsDate.between(compDate, new Date());
		}
		query.from(qCustomerComplaint).
		innerJoin(qCustomerComplaint.customer, qCustomer).fetch()
		//.innerJoin(qCustomerComplaint.qComplaintsReason, qComplaintsReason).fetch()
		
			.where(predicate)
			.orderBy(qCustomerComplaint.id.asc());
		
		return query.distinct().list(qCustomerComplaint);
	}
	
	
}
