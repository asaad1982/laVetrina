package com.salesmanager.web.shop.controller.sale.facade;

import java.util.List;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.sale.entity.SaleRequest;


public interface SaleFacade {

	List<Category> getAllCategories(MerchantStore store,Language language)throws ServiceException;

	List<Product> loadProducts(List<Long> categoryIds, Language language) throws ServiceException;
	
	void sendSaleRequest(SaleRequest saleRequest) throws Exception;

}
