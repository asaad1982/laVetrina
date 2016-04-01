package com.salesmanager.web.admin.controller.notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.notification.EmailNotification;
import com.salesmanager.core.business.notification.EmailTemplate;
import com.salesmanager.core.business.notification.service.EmailNotificationService;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promo.model.PromotionDescription;
import com.salesmanager.core.business.promo.model.PromotionRule;
import com.salesmanager.core.business.promo.model.PromotionTragetAge;
import com.salesmanager.core.business.promo.model.PromotionType;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.DateUtil;


@Controller
public class NotificationController {
	@Autowired
	CustomerService customerService;
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
	@Autowired
	EmailNotificationService emailNotificationService;
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/notification/editNotification.html", method=RequestMethod.GET)
	public String displayNotificationEdit(@RequestParam("id") long notificationId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayNotification(notificationId,model,request,response);

	}
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/notification/addNotification.html", method=RequestMethod.GET)
	public String displayNotificationAdd( Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayNotification(0,model,request,response);

	}
	private String displayNotification(long notificationId, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		setMenu(model,request);
		EmailNotification emailNotification=new EmailNotification();
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		if( notificationId!=0) {
			if(request.getSession().getAttribute("notification")==null){
				emailNotification=emailNotificationService.getById(notificationId);
			}else{
				emailNotification=(EmailNotification) request.getSession().getAttribute("promotion");
			}
		}else{
			emailNotification.setEmailTemplates(new ArrayList<EmailTemplate>());
		}
		model.addAttribute("notificationId", notificationId);
		List<Language> languages = store.getLanguages();
		List<EmailTemplate> emailTemplates=emailNotification.getEmailTemplates();
		for(Language language:languages){
			EmailTemplate emailTemplate = null;
			for(EmailTemplate desc : emailTemplates) {				
				if(desc.getLanguage().getCode().equals(language.getCode())) {
					emailTemplate = desc;
					
					
				}
		}
			if(emailTemplate==null) {
				emailTemplate = new EmailTemplate();
				emailTemplate.setLanguage(language);
				emailTemplate.setEmailNotification(emailNotification);
				emailNotification.getEmailTemplates().add(emailTemplate);
			}
			
			
			
		}
		
		
		model.addAttribute("languages",languages);
		model.addAttribute("emailNotification", emailNotification);
		
		
		return "notification";
	}
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/notification/notifications.html", method=RequestMethod.GET)
	public String displayNotification(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		setMenu(model,request);
		
		
		
		//does nothing, ajax subsequent request
		
		
		return "notifications";
	}
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/notification/paging.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String pageNotification(HttpServletRequest request, HttpServletResponse response) {
		String eventName = request.getParameter("eventName");
		
        
        String eventDate =request.getParameter("startDate");
        String endDate=request.getParameter("endDate");


		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Language language = (Language)request.getAttribute("LANGUAGE");
				
		
			
			
			List<EmailNotification> emailNotifications = null;
					
			if(!StringUtils.isBlank(eventDate) || !StringUtils.isBlank(eventName) ) {
//				
//				
				emailNotifications = emailNotificationService.listNotification(language, eventName,eventDate,endDate);
//				
			} else  {
//				
				//categoryService.listByCodes(store, new ArrayList<String>(Arrays.asList(categoryCode)), language);
			
			emailNotifications = emailNotificationService.list();
			}
				
			
			
			for(EmailNotification emailNotification : emailNotifications) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", emailNotification.getId());
				
				entry.put("eventName", emailNotification.getEventName());
				entry.put("eventDate", DateUtil.formatDate(emailNotification.getEventDate()));
				
				resp.addDataEntry(entry);
				
				
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging complaints", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/notification/save.html", method=RequestMethod.POST)
	public String saveComplaints(@Valid @ModelAttribute("emailNotification") EmailNotification emailNotification, BindingResult result, Model model, HttpServletRequest request) throws Exception {

		setMenu(model, request);
		//EmailNotification currNotification=emailNotificationService.getById(emailNotification.getId());
		if (result.hasErrors()) {
			return "notification";
		}
		EmailNotification emailNotification2=(EmailNotification) request.getSession().getAttribute("emailNotification");
		if(emailNotification2!=null){
			emailNotification.setCustomers(emailNotification2.getCustomers());
		}
		emailNotificationService.saveOrUpdate(emailNotification);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		List<Language> languages = store.getLanguages();
		
		model.addAttribute("languages",languages);
		model.addAttribute("emailNotification", emailNotification);
		model.addAttribute("success","success");
		return "notification";
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value = "/admin/notification/remove.html", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String deleteComplaints(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		AjaxResponse resp = new AjaxResponse();

		try {

			Long id = Long.parseLong(sid);

			EmailNotification emailNotification = new EmailNotification();
			emailNotification.setId(id);

			emailNotificationService.delete(emailNotification);
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while deleting category", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}

		String returnString = resp.toJSONString();

		return returnString;
	}
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/notificatin/customers/paging.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String pageCustomers(@RequestParam("id") long notificationId,HttpServletRequest request, HttpServletResponse response) {
		
		AjaxResponse resp = new AjaxResponse();

		try {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
	        String customerMail =request.getParameter("email");
	        String birthDate =request.getParameter("birthDate");
	        String ageRange=request.getParameter("ageRange");
	        String country=request.getParameter("country");
	        String gender=request.getParameter("gender");
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			EmailNotification emailNotification=null;
			if(request.getSession().getAttribute("notification")==null){
				emailNotification=emailNotificationService.getById(notificationId);
			}else{
				emailNotification=(EmailNotification) request.getSession().getAttribute("promotion");
			}
			if(emailNotification==null){
				emailNotification=new EmailNotification();
			}
			//get list of countries
			List<Customer> customers=null;
if(!StringUtils.isBlank(firstName)||!StringUtils.isBlank(lastName) || !StringUtils.isBlank(customerMail) || !StringUtils.isBlank(birthDate)|| !StringUtils.isBlank(country) || !StringUtils.isBlank(ageRange) || !StringUtils.isBlank(gender)) {
				
				
	customers = customerService.getBySearchCritera(firstName, lastName, gender, birthDate,country,customerMail );
				
			}else 
			 customers = customerService.listByStore(store);
			
			
			//get inclusions
			List<Customer> includedCountries = emailNotification.getCustomers();
			

			for(Customer customer : customers) {
				
				

				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", customer.getId());
				entry.put("firstName", customer.getBilling().getFirstName());
				entry.put("lastName", customer.getBilling().getLastName());
				entry.put("email", customer.getEmailAddress());
				entry.put("gender", customer.getGender()==null?"":customer.getGender().toString());
				entry.put("birthDate", DateUtil.formatDate(customer.getDateOfBirth()));
				entry.put("ageRange",DateUtil.getAgeRange(customer.getDateOfBirth()) );
				entry.put("interset","" );
				if(includedCountries!=null &&includedCountries.contains(customer)) {
					entry.put("supported", true);
				} else {
					entry.put("supported", false);
				}
				
				
				resp.addDataEntry(entry);
				
			}
			request.getSession().setAttribute("emailNotification",emailNotification);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging shipping countries", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/notificatin/customers/update.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String updateCustomers(HttpServletRequest request, HttpServletResponse response) {
		String values = request.getParameter("_oldValues");
		String supported = request.getParameter("supported");
		String id = request.getParameter("id");
		
		

		
		
		AjaxResponse resp = new AjaxResponse();

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("rawtypes")
			Map conf = mapper.readValue(values, Map.class);
			
			int customerId = (int)conf.get("id");

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			EmailNotification emailNotification=(EmailNotification) request.getSession().getAttribute("emailNotification");
			if(emailNotification.getCustomers()==null){
				emailNotification.setCustomers(new ArrayList<Customer>() );
				
			}
			
			if(!StringUtils.isBlank(supported)) {
				if("true".equals(supported)) {
					emailNotification.getCustomers().add(customerService.getById((long) customerId));
				} else {
					emailNotification.getCustomers().remove(customerId);
				}
			}
			//promotion.getPromotionRule().setCountries(includedCountries);
			request.getSession().setAttribute("emailNotification",emailNotification);
			//promotionService.saveOrUpdate(promotion);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging shipping countries", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		
		activeMenus.put("EmailConfig", "EmailConfig");
		activeMenus.put("create-email", "create-email");
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("configuration");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
}
