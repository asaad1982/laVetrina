package com.salesmanager.core.business.notification.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.notification.EmailNotification;
@Repository("emailDao")
public class EmailDaoImpl extends SalesManagerEntityDaoImpl<Long, EmailNotification> implements EmailNotificationDao{

	@Override
	public List<EmailNotification> listNotifications(String eventName,
			String eventDate) {
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct em from EmailNotification as em ");
		 if(eventName!=null && eventDate!=null)
				qs.append("where em.eventName like (:eventName) and em.eventDate=:evenDate");
		 else if(eventName!=null)
		qs.append("where em.eventName like (:eventName)");
		 if(eventDate!=null)
				qs.append("where em.eventDate like (:eventDate)");


    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);
		if(eventName!=null)
    	q.setParameter("eventName", eventName);
		if(eventDate!=null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date compDate=new Date();
			try {
				compDate = formatter.parse(eventDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			q.setParameter("eventDate", compDate);
		}

    	
    	@SuppressWarnings("unchecked")
		List<EmailNotification> emailNotifications =  q.getResultList();

    	
    	return emailNotifications;

	}

}
