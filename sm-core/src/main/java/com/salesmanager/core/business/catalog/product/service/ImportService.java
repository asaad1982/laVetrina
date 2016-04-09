package com.salesmanager.core.business.catalog.product.service;

import com.paypal.core.soap.MerchantAPICallPreHandler;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ImportService {
	public void importFile(FileBean fileBean,MerchantStore store,Language language) throws ServiceException;

	void importCustomerFile(FileBean fileBean, MerchantStore store, Language language) throws ServiceException;
}
