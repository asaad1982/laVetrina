package com.salesmanager.web.shop.controller.sale.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.sale.model.SaleRequest;
import com.salesmanager.core.business.sale.service.SaleService;


@Service( value = "saleFacade" )
public class SaleFacadeImpl implements SaleFacade {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleService saleService;
	
	@Override
	public List<Category> getAllCategories(Language language){
		
		return categoryService.listAll(language);
	}

	@Override
	public List<Product> loadProducts(List<Long> categoryIds, Language language) throws ServiceException {

		return productService.getProducts(categoryIds, language);
	}

	@Override
	public void sendSaleRequest(SaleRequest saleRequest) throws Exception {

		saleService.sendSaleRequest(saleRequest);
	}

}
