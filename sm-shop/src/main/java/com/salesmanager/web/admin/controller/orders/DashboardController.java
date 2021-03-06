package com.salesmanager.web.admin.controller.orders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.SalesReport;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.model.OrderCriteria;
import com.salesmanager.core.business.order.model.OrderList;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatus;
import com.salesmanager.core.business.order.service.OrderService;
import com.salesmanager.core.business.payments.model.PaymentType;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.business.system.service.ModuleConfigurationService;
import com.salesmanager.core.utils.ProductPriceUtils;
import com.salesmanager.core.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.DateUtil;
import com.salesmanager.web.utils.LabelUtils;


/**
 * Manage order list
 * Manage search order
 * @author csamson 
 *
 */
@Controller
public class DashboardController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	LabelUtils messages;
	
	@Autowired
	private ProductPriceUtils priceUtil;
	
	@Autowired
	protected ModuleConfigurationService moduleConfigurationService;
	 
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderControler.class);

	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/dashboardsProductCatalog.html", method=RequestMethod.GET)
	public String dashboardsProductCatalog(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request,"order-product-catalog");

		//the list of orders is from page method
		
		
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		 Map<String, Long> map = new HashMap<String, Long>();
			
			long total=0;
			
		
		List<Object[]> countProductsByCategories = categoryService.countProductsByCategories(store);
		
		 
		
		
//		for(Object[] counts : countProductsByCategories) {
//			Category c = (Category)counts[0];
//			map.put(c.getDescription().getName(), (Long)counts[1]);
//			total = total+((Long)counts[1]).longValue();
//
//		}
		
		for(Object[] counts : countProductsByCategories) {
			 
			map.put((String)counts[2], ((BigInteger)counts[1]).longValue());
			total = total+((BigInteger)counts[1]).longValue();

		}
		
		
		
	
		model.addAttribute("map", map);
		model.addAttribute("total", total);
		

		
		return ControllerConstants.Tiles.Dashboeard.dashboardsProductCatalog;
		
		
	}
	
	
 
	
 
	

	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/dashboardsProductPrice.html", method=RequestMethod.GET)
	public String dashboardsProductPrice(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request,"order-product-price");

		//the list of orders is from page method
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		List<Object[]> list = 	productService.listByStoreGroupByPrices(store);
		
		Map<Double,Long> map = new HashMap<Double,Long>();
for(Object[] lst : list){
	
	map.put(((BigDecimal)lst[0]).doubleValue(), ((BigInteger)lst[1]).longValue());
	
}

model.addAttribute("map", map);

		
		return ControllerConstants.Tiles.Dashboeard.dashboardsProductPrice;
		
		
	}
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/dashboardsDeals.html", method=RequestMethod.GET)
	public String dashboardsDeals(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request,"order-deals");

		//the list of orders is from page method
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
long total = 0 ;
		List<Object[]> list = 	orderService.countOrderByStatus(store);
		
		Map<String,Long> map = new HashMap<String,Long>();
for(Object[] lst : list){
	
	map.put(((OrderStatus)lst[0]).getValue(), ((Long)lst[1]).longValue());
	total = total+((Long)lst[1]).longValue();
}

model.addAttribute("map", map);
model.addAttribute("total", total);	
		return ControllerConstants.Tiles.Dashboeard.dashboardsDeals;
		
		
	}
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/dashboardsSoldInstock.html", method=RequestMethod.GET)
	public String dashboardsSoldInstock(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request,"order-sold-instock");

		//the list of orders is from page method
		
		
		
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		List<Object[]> list = 	productService.listSoldInstoc(store);
		List<ProductStock> stock = new ArrayList<ProductStock>();
		Map<Double,Long> map = new HashMap<Double,Long>();
for(Object[] lst : list){
	ProductStock productStock = new ProductStock();
	productStock.setId(((BigInteger)lst[0]).longValue());
	productStock.setName((String)lst[1]);
	productStock.setQuantity(((Integer)lst[2]).longValue());
	productStock.setQuantity_ordered(((BigInteger)lst[3]).longValue());
	
	stock.add(productStock);
}

model.addAttribute("stock", stock);

		
		
		return ControllerConstants.Tiles.Dashboeard.dashboardsSoldInstock;
		
		
	}
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/dashboardsSalesCountry.html", method=RequestMethod.GET)
	public String dashboardsSalesCountry(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request,"order-sales-country");
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

			

		


List<Object[]> countOrderByCountry =orderService.countOrderByCountry(store);;
List<Country> countries = null;

if(countOrderByCountry.size()>0){
Language language = (Language)request.getAttribute("LANGUAGE");
 countries = countryService.getCountries(language);
}

Map <String,Long> map= new HashMap<String,Long>();

for(Object[] counts : countOrderByCountry) {
	
	for(Country contry:countries)
	{
		if(((Country)counts[0]).getId().intValue() ==contry.getId().intValue() )
		{		map.put(contry.getDescriptions().get(0).getName(), (Long)counts[1]);
		break;}
	}
	

}


		model.addAttribute("map", map);
 
		return ControllerConstants.Tiles.Dashboeard.dashboardsSalesCountry;
		
		
	}
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/dashboardsSalesPayment.html", method=RequestMethod.GET)
	public String dashboardsSalesPayment(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request,"order-sales-instock");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		 
				List<Object[]> list = 	orderService.countOrderByPaymentType(store);
				
				Map<PaymentType,Long> map = new HashMap<PaymentType,Long>();
		for(Object[] lst : list){
			
			map.put(((PaymentType)lst[0]) , ((Long)lst[1]).longValue());
			 
		}

		model.addAttribute("map", map);
	 			
		return ControllerConstants.Tiles.Dashboeard.dashboardsSalesPayment;
		
		
	}
	
	
	
	
//	private void setMenu(Model model, HttpServletRequest request) throws Exception {
//		
//		//display menu
//		Map<String,String> activeMenus = new HashMap<String,String>();
//	
//		activeMenus.put("order-dashboard", "order-dashboard");
//		activeMenus.put("order-product-catalog", "order-product-catalog");
//		
//
//		@SuppressWarnings("unchecked")
//		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
//		
//		Menu currentMenu = (Menu)menus.get("order-dashboard");
//		model.addAttribute("currentMenu",currentMenu);
//		model.addAttribute("activeMenus",activeMenus);
//		//
//		
//	}
//	
	
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/soldVsInstock.html", method=RequestMethod.GET)
	public String soldVsInstock(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		setMenu(model,request,"order-soldVsInstock");

		//the list of orders is from page method
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
long totalQuantity=0;
long totalQuantity_ordered=0;
		List<Object[]> list = 	productService.listSoldInstoc(store);
	 
for(Object[] lst : list){
 
 
	totalQuantity = totalQuantity +((Integer)lst[2]).longValue();
	totalQuantity_ordered = totalQuantity_ordered + ((BigInteger)lst[3]).longValue();
}

model.addAttribute("totalQuantity", totalQuantity);

model.addAttribute("totalQuantity_ordered", totalQuantity_ordered);

	
	
		return ControllerConstants.Tiles.Dashboeard.dashboardssoldVsInstock;
		
		
	}
	
	
	
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/customersStatistics.html", method=RequestMethod.GET)
	public String customersStatistics(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		setMenu(model,request,"dash-board-customersStatistics");

		//the list of orders is from page method
		
		String startDate = request.getParameter("startDate");        
       
		model.addAttribute("startDate", startDate);
	
	
		return ControllerConstants.Tiles.Dashboeard.dashboardscustomerStatsitics;
		
		
	}
	
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/customersStatistics/paging.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String customersStatisticspaging(HttpServletRequest request, HttpServletResponse response) {
		String dateInString = request.getParameter("startDate");        
		String year =null;
		String month = null;
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		 
 
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 
		Date date = null;
		try {

			{if(dateInString != null && !"".equals(dateInString))
			
			  date = formatter.parse(dateInString);
	
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			
			int year1 = calendar.get(Calendar.YEAR);
			//Add one to month {0 - 11}
			int month1 = calendar.get(Calendar.MONTH) + 1;
			year=year1+"";
			month = month1+"";
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
 
		
        AjaxResponse resp = new AjaxResponse();		
		try {			
			//Language language = (Language)request.getAttribute("LANGUAGE");
		
					List<Object[]> list = 	productService.customersStatistics(store, year, month);
				
					
			for(Object[] lst : list) {				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("buyerName", ((String)lst[0]));				
				entry.put("quantityName", ((BigDecimal)lst[1]).longValue());
				entry.put("amountName", ((BigDecimal)lst[2]).longValue());
				 
				resp.addDataEntry(entry);
				
				
			}
			
			
	 
			
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging sales report", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	
	
	
	
	
	
	
	
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/orders/availabilityCategory.html", method=RequestMethod.GET)
	public String availabilityCategory(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		setMenu(model,request,"dash-board-availabilityCategory");

		//the list of orders is from page method

		return ControllerConstants.Tiles.Dashboeard.dashboardsavailabilityCategory;
		
		
	}
	
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/availabilityCategory/paging.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String availabilityCategorypaging(HttpServletRequest request, HttpServletResponse response) {
		String dateInString = request.getParameter("startDate");        
		String year =null;
		String month = null;
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		 
 
		
	 
		
		
 
		
        AjaxResponse resp = new AjaxResponse();		
		try {			
			//Language language = (Language)request.getAttribute("LANGUAGE");
		
					List<Object[]> list = 	categoryService.categoryStockAvailability(store);
				
					
			for(Object[] lst : list) {				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("subCategory", ((String)lst[1]));				
				entry.put("instock", ((BigInteger)lst[2]).longValue());
 				 
				resp.addDataEntry(entry);
				
				
			}
			
 
	 
			
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging sales report", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	
	
	
	
	
	private void setMenu(Model model, HttpServletRequest request,String menu) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
	
		activeMenus.put("order-dashboard", "order-dashboard");
		activeMenus.put(menu, menu);
		

		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("order-dashboard");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
