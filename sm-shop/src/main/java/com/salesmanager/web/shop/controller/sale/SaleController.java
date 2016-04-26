package com.salesmanager.web.shop.controller.sale;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.sale.entity.ProductSaleRequest;
import com.salesmanager.core.business.sale.entity.SaleRequest;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.shop.controller.sale.facade.SaleFacade;
import com.salesmanager.web.shop.controller.sale.form.SaleRequestForm;
import com.salesmanager.web.shop.controller.sale.model.ProductModel;


@Controller
public class SaleController {
	

	@Autowired
	private SaleFacade saleFacade;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);
	
	
	@RequestMapping(value="/shop/sale/wholeSale.html", method=RequestMethod.GET)
	public String wholeSale(Model model, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		LOGGER.info("whole sale entry page");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		SaleRequestForm saleRequestForm = new SaleRequestForm();
		
		request.getSession().setAttribute("productSaleRequests", null);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		List<Category> categories = saleFacade.getAllCategories(store,language);
		
		request.getSession().setAttribute("categories", categories);
		
		model.addAttribute("categories", categories);
		model.addAttribute("saleRequestForm", saleRequestForm);
		return "wholeSale.page";
	}
	
	@RequestMapping(value="/shop/sale/wholeSale.html", method=RequestMethod.POST)
	public String saveContent(@Valid @ModelAttribute SaleRequestForm saleRequestForm, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		
		request.setAttribute("saleRequestForm", saleRequestForm);
		
		if (result.hasErrors()) {
			model.addAttribute("saleRequestForm", saleRequestForm);
			return "wholeSale.page";
		}
		
		SaleRequest saleRequest = new SaleRequest();
		saleRequest.setCustomerEmail(saleRequestForm.getCustomerEmail());
		saleRequest.setCustomerMobile(saleRequestForm.getCustomerMobile());
		saleRequest.setCustomerName(saleRequestForm.getCustomerName());
		
		saleRequest.setProductSaleRequests((List<ProductSaleRequest>) request.getSession().getAttribute("productSaleRequests"));
		
		
		saleFacade.sendSaleRequest(saleRequest);
		
		redirectAttributes.addFlashAttribute("successMsgCode", "message.wholeSale.success");
		
		return "redirect:/shop/sale/wholeSale.html";
		
	}
	
	@RequestMapping(value = "/shop/sale/loadProducts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ProductModel> loadProducts(HttpServletRequest request,
			@RequestParam("categoryId") String categoryId) throws ServiceException {

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		List<Product> products = new ArrayList<Product>();
		List<Long> categoryIds = new ArrayList<Long>();
		if (categoryId != null && !categoryId.isEmpty()) {
			long category = Long.parseLong(categoryId);
			categoryIds.add(category);
			products = saleFacade.loadProducts(categoryIds, language);
		}

		List<ProductModel> pm = new ArrayList<ProductModel>();
		for(Product p:products){
			
			ProductModel m = new ProductModel();
			m.setId(p.getId());
			m.setName( ((ProductDescription) p.getDescriptions().toArray()[0]).getName());
			
			pm.add(m);
		}
		
		request.setAttribute("products", pm);
		return pm;
	}

	@RequestMapping(value = "/shop/sale/populateProduct", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String populateProduct(HttpServletRequest request,
			@RequestParam("productId") String productId, @RequestParam("quantity") String quantity) throws ServiceException {

		long product=0;
		Integer productQuantity=0;
		if (productId != null && !productId.isEmpty()) {
			product = Long.parseLong(productId);
		}
		
		if (quantity != null && !quantity.isEmpty()) {
			productQuantity = Integer.parseInt(quantity);
		}

		ProductSaleRequest productSaleRequest = new ProductSaleRequest();
		productSaleRequest.setProductId(product);
		productSaleRequest.setQuantity(productQuantity);
		
		List<ProductSaleRequest> productSaleRequests = (List<ProductSaleRequest>) request.getSession().getAttribute("productSaleRequests");
		if(productSaleRequests==null)
			productSaleRequests = new ArrayList<ProductSaleRequest>();
		
		productSaleRequests.add(productSaleRequest);
		
		request.getSession().setAttribute("productSaleRequests", productSaleRequests);
		
		return "success";
	}

	
	@ExceptionHandler({Exception.class})
	public ModelAndView  saleControlerHandler(HttpServletRequest req,
			Exception e) {

		LOGGER.error("saleControllerHandler :: ", e);
		ModelAndView mav = new ModelAndView();
		SaleRequestForm saleRequestForm = (SaleRequestForm) req.getAttribute("saleRequestForm");
		
		if(saleRequestForm == null)
			saleRequestForm = new SaleRequestForm();
		
		mav.getModelMap().addAttribute("saleRequestForm", saleRequestForm);
		mav.getModelMap().addAttribute("msgCode","message.wholeSale.error");
		mav.setViewName("wholeSale.page");
		
		return mav;
		
	}
}
