package com.salesmanager.web.admin.controller.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.product.model.SalesReport;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.notification.EmailNotification;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.DateUtil;


@Controller
public class ReportsController {
	
	public ReportsController() {
		month.put(1, "Jan");
		month.put(2, "Feb");
		month.put(3, "Mar");
		month.put(4, "Apr");
		month.put(5, "May");
		month.put(6, "Jun");
		month.put(7, "July");
		month.put(8, "Aug");
		month.put(9, "Sep");
		month.put(10, "Oct");
		month.put(11, "Nov");
		month.put(12, "Dec");
	}
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/report/reports.html", method=RequestMethod.GET)
	public String displayreport(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		setMenu(model,request,"sales-Report");
		String startDate = request.getParameter("startDate");        
        String endDate =request.getParameter("endDate");
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		//does nothing, ajax subsequent request
		
		
		return "reports";
	}
	private HashMap< Integer, String> month=new HashMap< Integer, String>();
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/report/paging.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pagereport(HttpServletRequest request, HttpServletResponse response) {
		String startDate = request.getParameter("startDate");        
        String endDate =request.getParameter("endDate");
        AjaxResponse resp = new AjaxResponse();		
		try {			
			Language language = (Language)request.getAttribute("LANGUAGE");
			if(startDate!=null && endDate!=null){
			List<SalesReport> salesReports=productService.getProductsSales(language.getId(), startDate, endDate);
			for(SalesReport salesReport : salesReports) {				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("categoryId", salesReport.getProductCategoryId());				
				entry.put("categoryName", salesReport.getProductCategoryName());
				entry.put("productName", salesReport.getProductName());
				entry.put("productSku", salesReport.getProductSKU());				
				entry.put(month.get(salesReport.getMonth()), salesReport.getTotalSales());
				entry.put("Total", salesReport.getTotalSales());
				resp.addDataEntry(entry);
				
				
			}
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
