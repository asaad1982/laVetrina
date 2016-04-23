package com.salesmanager.core.business.sale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.sale.dao.SaleRequestDao;
import com.salesmanager.core.business.sale.model.SaleRequest;

@Service("saleService")
public class SaleServiceImpl extends
		SalesManagerEntityServiceImpl<Long, SaleRequest> implements SaleService {

	SaleRequestDao saleRequestDao;


	@Autowired
	public SaleServiceImpl(SaleRequestDao saleRequestDao) {
		super(saleRequestDao);
		this.saleRequestDao = saleRequestDao;

	}


}
