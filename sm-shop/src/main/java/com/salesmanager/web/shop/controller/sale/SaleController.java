package com.salesmanager.web.shop.controller.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.sale.entity.ProductSaleRequest;
import com.salesmanager.core.business.sale.entity.SaleRequest;
import com.salesmanager.core.business.system.service.EmailService;
import com.salesmanager.core.modules.email.Email;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.constants.EmailConstants;
import com.salesmanager.web.shop.controller.sale.facade.SaleFacade;
import com.salesmanager.web.shop.controller.sale.form.SaleRequestForm;
import com.salesmanager.web.shop.controller.sale.model.ProductModel;
import com.salesmanager.web.utils.EmailUtils;
import com.salesmanager.web.utils.FilePathUtils;
import com.salesmanager.web.utils.LabelUtils;
import com.salesmanager.web.utils.LocaleUtils;


@Controller
public class SaleController {


	@Autowired
	private SaleFacade saleFacade;

	@Autowired
	private EmailService emailService;

	@Autowired
	LabelUtils messages;

	@Autowired
	LanguageService languageService;

	private final static String NEW_STORE_TMPL = "email_template_new_store.ftl";

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

		List<ProductSaleRequest> productSaleRequests = (List<ProductSaleRequest>) request.getSession().getAttribute("productSaleRequests");
		if(productSaleRequests == null || productSaleRequests.size()<1){
			model.addAttribute("msgCode","validation.wholeSale.productId.required");
			model.addAttribute("saleRequestForm", saleRequestForm);
			return "wholeSale.page";
		}
			

		SaleRequest saleRequest = new SaleRequest();
		saleRequest.setCustomerEmail(saleRequestForm.getCustomerEmail());
		saleRequest.setCustomerMobile(saleRequestForm.getCustomerMobile());
		saleRequest.setCustomerName(saleRequestForm.getCustomerName());

		saleRequest.setProductSaleRequests(productSaleRequests);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		sendEmail(store, request, saleRequest);
		
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
			@RequestParam("productId") String productId, @RequestParam("quantity") String quantity, @RequestParam("action") String action) throws ServiceException {

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

		if(action.equals("add"))
			productSaleRequests.add(productSaleRequest);
		else if(action.equals("delete")){
			for(ProductSaleRequest req:productSaleRequests){
				if(req.getProductId().equals(productSaleRequest.getProductId()) && req.getQuantity().equals(productSaleRequest.getQuantity())){
					productSaleRequests.remove(req);
					break;
				}
			}
		}
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

	private void sendEmail(MerchantStore store, HttpServletRequest request, SaleRequest saleRequest){

		try {
			Language defaultLanguage = store.getDefaultLanguage();
			defaultLanguage = languageService.getById(defaultLanguage.getId());
			if(defaultLanguage!=null) {
				store.setDefaultLanguage(defaultLanguage);
			}

			Locale storeLocale = LocaleUtils.getLocale(defaultLanguage);
			Map<String, String> templateTokens = EmailUtils.createEmailObjectsMap(request.getContextPath(), store, messages, storeLocale);
			templateTokens.put(EmailConstants.EMAIL_NEW_STORE_TEXT, messages.getMessage("email.newstore.text", storeLocale));
			templateTokens.put(EmailConstants.EMAIL_STORE_NAME, messages.getMessage("email.newstore.name",new String[]{store.getStorename()},storeLocale));
			templateTokens.put(EmailConstants.EMAIL_ADMIN_STORE_INFO_LABEL, messages.getMessage("email.newstore.info",storeLocale));

			templateTokens.put(EmailConstants.EMAIL_ADMIN_URL_LABEL, messages.getMessage("label.adminurl",storeLocale));
			templateTokens.put(EmailConstants.EMAIL_ADMIN_URL, FilePathUtils.buildAdminUri(store, request));


			Email email = new Email();
			email.setFrom(store.getStorename());
			email.setFromEmail(saleRequest.getCustomerEmail());
			email.setSubject(messages.getMessage("email.newstore.title",storeLocale));
			email.setTo("eng.amiranagi@gmail.com");
			email.setTemplateName(NEW_STORE_TMPL);
			email.setTemplateTokens(templateTokens);

			emailService.sendHtmlEmail(store, email);

		} catch (Exception e) {
			LOGGER.error("Cannot send email to user",e);
		}
	}

}
