package com.salesmanager.web.shop.controller.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.promo.model.PromotionModel;
import com.salesmanager.core.business.promotion.service.PromotionService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;
import com.salesmanager.web.populator.catalog.ReadableProductPopulator;
import com.salesmanager.web.shop.controller.ControllerConstants;
import com.salesmanager.web.shop.controller.customer.facade.CustomerFacade;
import com.salesmanager.web.utils.BreadcrumbsUtils;

@Controller
public class CustomerPromotionController {
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private BreadcrumbsUtils breadcrumbsUtils;
	@Autowired
    private CustomerFacade customerFacade;
	@Autowired
	private PricingService pricingService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerPromotionController.class);
	@RequestMapping("/shop/promotion/hotdeals.html")
	public String displayHotDeals( Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = null;
    	if(auth != null ) {
    		customer = customerFacade.getCustomerByEmail(auth.getName(), store);

        }
		List<PromotionModel> promotions=promotionService.listPromotionActive(store,language,customer);
		LOGGER.debug("Pr:"+promotions);
		LOGGER.debug("Pr:"+promotions.size());
		PromotionModel promotionModel=promotions.get(promotions.size()-1);
		LOGGER.debug("PromotionModel:"+promotionModel);
		if(promotionModel!=null && promotionModel.getPromotionId()==0){
			List<ReadableProduct> productItems = new ArrayList<ReadableProduct>();
			ReadableProductPopulator populator = new ReadableProductPopulator();
			populator.setPricingService(pricingService);
			LOGGER.debug("promotionModel.getProducts()"+promotionModel.getProducts());
			for(Product product : promotionModel.getProducts()) {
				
				
				ReadableProduct proxyProduct = populator.populate(product, new ReadableProduct(), store, language);
				LOGGER.debug("proxyProduct"+proxyProduct);
				productItems.add(proxyProduct);
			}
			LOGGER.debug("productItems"+productItems);
			model.addAttribute("productsDiscount", productItems);

		}
		model.addAttribute("promotions", promotions);
		/** template **/
		StringBuilder template = new StringBuilder().append("hotdeals").append(".").append(store.getStoreTemplate());

		return template.toString();
	}
}
