package com.salesmanager.core.business.notification.service;



import java.util.List;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.notification.EmailNotification;
import com.salesmanager.core.business.reference.language.model.Language;

public interface EmailNotificationService extends SalesManagerEntityService<Long, EmailNotification>{
	void saveOrUpdate(EmailNotification emailNotification) throws ServiceException;

	List<EmailNotification> listNotification(Language language,
			String eventName, String eventDate)throws ServiceException;
}
