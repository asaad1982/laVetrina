package com.salesmanager.web.shop.controller.sale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.sale.service.SaleService;
import com.salesmanager.web.utils.LabelUtils;


@Controller
public class SaleController {
	

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private SaleService saleService;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);
	
	
	

}
