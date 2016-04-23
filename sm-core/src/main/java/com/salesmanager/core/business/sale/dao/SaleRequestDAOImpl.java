package com.salesmanager.core.business.sale.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.sale.model.SaleRequest;

@Repository("saleRequestDao")
public class SaleRequestDAOImpl extends SalesManagerEntityDaoImpl<Long, SaleRequest> implements SaleRequestDao {

	public SaleRequestDAOImpl() {
		super();
	}
	
	

}
