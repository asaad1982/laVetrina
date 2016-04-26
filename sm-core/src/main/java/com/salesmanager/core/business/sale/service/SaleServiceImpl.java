package com.salesmanager.core.business.sale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.sale.dao.SaleRequestDao;
import com.salesmanager.core.business.sale.entity.ProductSaleRequest;
import com.salesmanager.core.business.sale.entity.SaleRequest;
import com.salesmanager.core.business.sale.util.EmailClient;

@Service("saleService")
public class SaleServiceImpl extends
		SalesManagerEntityServiceImpl<Long, SaleRequest> implements SaleService {

	SaleRequestDao saleRequestDao;
	
	@Autowired
	public SaleServiceImpl(SaleRequestDao saleRequestDao) {
		super(saleRequestDao);
		this.saleRequestDao = saleRequestDao;

	}


	@Override
	public void sendSaleRequest(SaleRequest saleRequest) throws Exception {

		//send email
//		sendEmail(saleRequest);
		
		//save request
		for(ProductSaleRequest psr:saleRequest.getProductSaleRequests()){
			psr.setSaleRequest(saleRequest);
		}
		saleRequestDao.save(saleRequest);

	}


	private void sendEmail(SaleRequest saleRequest) throws Exception {

		String from = saleRequest.getCustomerEmail();
		String subject = "Whole Sale Request";
		String mailBody ="Whoe Sale Request, Request Number: " + saleRequest.getRequestNumber();

		EmailClient.sendMail(from, "eng.amiranagi@gmail.com", subject, mailBody);
		
	}


}
