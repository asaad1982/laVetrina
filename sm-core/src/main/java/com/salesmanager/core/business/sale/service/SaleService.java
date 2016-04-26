package com.salesmanager.core.business.sale.service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.sale.entity.SaleRequest;

public interface SaleService extends SalesManagerEntityService<Long, SaleRequest> {

	void sendSaleRequest(SaleRequest saleRequest) throws Exception;



}
