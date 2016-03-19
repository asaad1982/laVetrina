package com.salesmanager.core.business.catlog.complaints;


import java.util.List;



import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ComplaintsService extends SalesManagerEntityService<Long, ComplaintsReason>{
	List<ComplaintsReason> listByStore( Language language)
			throws ServiceException;

	void saveOrUpdate(ComplaintsReason category) throws ServiceException;

	List<ComplaintsReason> getByName(String categoryName, Language language) throws ServiceException;
}
