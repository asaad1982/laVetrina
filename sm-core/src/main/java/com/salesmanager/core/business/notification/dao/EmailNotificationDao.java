package com.salesmanager.core.business.notification.dao;


import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.notification.EmailNotification;

public interface EmailNotificationDao extends SalesManagerEntityDao<Long, EmailNotification>{

	List<EmailNotification> listNotifications(String eventName, String eventDate);

}
