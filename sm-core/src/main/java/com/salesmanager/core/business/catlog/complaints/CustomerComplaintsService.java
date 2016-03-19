package com.salesmanager.core.business.catlog.complaints;

import java.util.List;

import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;

public interface CustomerComplaintsService extends SalesManagerEntityService<Long, CustomerComplaint>{
	List<CustomerComplaint> listByStore()
			throws ServiceException;

	void saveOrUpdate(CustomerComplaint customerComplaint) throws ServiceException;

	List<CustomerComplaint> getByName(String note,String status) throws ServiceException;

	List<CustomerComplaint> getByName(String note, String parameter,
			String customerMail, String reason, String date) throws ServiceException;


}
