package com.salesmanager.core.business.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaints.dao.ComplaintsReasonDAO;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.notification.EmailNotification;
import com.salesmanager.core.business.notification.dao.EmailNotificationDao;
import com.salesmanager.core.business.reference.language.model.Language;
@Service("emailNotificationService")
public class EmailNotificationServiceImpl extends SalesManagerEntityServiceImpl<Long, EmailNotification> implements EmailNotificationService {

	private EmailNotificationDao emailNotificationDao;
	@Autowired
	public EmailNotificationServiceImpl(
			EmailNotificationDao emailNotificationDao) {
		super(emailNotificationDao);
		this.emailNotificationDao=emailNotificationDao;
	}
	@Override
	public void saveOrUpdate(EmailNotification emailNotification)
			throws ServiceException {
		if(emailNotification.getId()!=null && emailNotification.getId()>0) {

			super.update(emailNotification);
			
		} else {
			
			super.save(emailNotification);
			
		}

		
	}
	@Override
	public List<EmailNotification> listNotification(Language language,
			String eventName, String eventDate) throws ServiceException {
		// TODO Auto-generated method stub
		return emailNotificationDao.listNotifications(eventName,eventDate);
	}

}
