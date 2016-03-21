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
import com.salesmanager.core.business.notification.EmailTemplate;
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
			List<EmailTemplate> emailTemplates=emailNotification.getEmailTemplates();
			emailNotification.setEmailTemplates(null);
			super.save(emailNotification);
			for(EmailTemplate emailTemplate:emailTemplates){
				emailTemplate.setEmailNotification(emailNotification);
			}
			emailNotification.setEmailTemplates(emailTemplates);
			
		}

		
	}
	@Override
	public List<EmailNotification> listNotification(Language language,
			String eventName, String eventDate,String endDate) throws ServiceException {
		// TODO Auto-generated method stub
		return emailNotificationDao.listNotifications(eventName,eventDate,endDate);
	}

}
