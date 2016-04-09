package com.salesmanager.web.admin.controller.complaints;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catlog.complaints.ComplaintsService;
import com.salesmanager.core.business.catlog.complaints.CustomerComplaintsService;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.DateUtil;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class ComplaintsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComplaintsController.class);	
	
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
	@Autowired
	ComplaintsService complaintsService;
	@Autowired
	CustomerComplaintsService  customerComplaintsService;
	@Autowired
	LabelUtils messages;

	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/editCategory.html", method=RequestMethod.GET)
	public String displayCategoryEdit(@RequestParam("id") long categoryId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayCategory(categoryId,model,request,response);

	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/createCategory.html", method=RequestMethod.GET)
	public String displayCategoryCreate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayCategory(null,model,request,response);

	}
	
	
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/customercomplaints/editComplaint.html", method=RequestMethod.GET)
	public String displayComplaintEdit(@RequestParam("id") long categoryId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayComplaint(categoryId,model,request,response);

	}
	
	
	
	private String displayCategory(Long categoryId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		//get parent complaints
		List<ComplaintsReason> complaints = complaintsService.listByStore(language);
		

		List<Language> languages = store.getLanguages();
		ComplaintsReason category = new ComplaintsReason();
		
		if(categoryId!=null && categoryId!=0) {//edit mode
			category = complaintsService.getById(categoryId);
		
			
			
			if(category==null ) {
				return "catalogue-complaints";
			}
		}
		
		
	

		
		model.addAttribute("category", category);
		model.addAttribute("complaints", complaints);
		

		
		return "complaints-reason";
	}
	
	
private String displayComplaint(Long complaintId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		
		
		
		
		CustomerComplaint customerComplaint = new CustomerComplaint();
		
		if(complaintId!=null && complaintId!=0) {//edit mode
			customerComplaint = customerComplaintsService.getById(complaintId);
		
			
			
			if(customerComplaint==null ) {
				return "customer-complaints";
			}
			
		}
		
		
	

		
		model.addAttribute("customerComplaint", customerComplaint);
		
		

		
		return "customercomplaint";
	}

@PreAuthorize("hasRole('AUTH')")
@RequestMapping(value="/admin/customercomplaints/save.html", method=RequestMethod.POST)
public String saveComplaints(@Valid @ModelAttribute("customerComplaint") CustomerComplaint customerComplaint, BindingResult result, Model model, HttpServletRequest request) throws Exception {

	setMenu(model, request);
	CustomerComplaint currentComplaint = customerComplaintsService.getById(customerComplaint.getId());
	customerComplaint.setCustomerComplaintReason(currentComplaint.getCustomerComplaintReason());
	customerComplaint.setComplaintsDate(currentComplaint.getComplaintsDate());
	customerComplaint.setCustomer(currentComplaint.getCustomer());
	model.addAttribute("customerComplaint", customerComplaint);
	if (result.hasErrors()) {
		return "customercomplaint";
	}
	
	customerComplaintsService.saveOrUpdate(customerComplaint);
	model.addAttribute("success","success");
	return "customercomplaint";
}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/save.html", method=RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute("category") ComplaintsReason category, BindingResult result, Model model, HttpServletRequest request,Locale locale) throws Exception {

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		//display menu
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		if(category.getId() != null && category.getId() >0) { //edit entry
			
			//get from DB
			ComplaintsReason currentCategory = complaintsService.getById(category.getId());
			
			if(currentCategory==null ) {
				return "catalogue-complaints";
			}

		}

			if(category.getEnglishName()!=null && "".equalsIgnoreCase(category.getEnglishName().trim())){
				ObjectError error = new ObjectError("englishName",messages.getMessage("Pattern.category.englishName", locale));
				result.addError(error);
			}
			if(category.getArabicName()!=null && "".equalsIgnoreCase(category.getArabicName().trim())){
				ObjectError error = new ObjectError("arabicName",messages.getMessage("Pattern.category.arabicName", locale));
				result.addError(error);
			}
			List<ComplaintsReason> complaintsReason=complaintsService.getByName(category.getEnglishName(), language);
			if(complaintsReason!=null && complaintsReason.size()>0){
				if(category.getId()==null ){
					ObjectError error = new ObjectError("englishName",messages.getMessage("complaint.name", locale));
					result.addError(error);
				}
			}
			
		
		if (result.hasErrors()) {
			return "complaints-reason";
		}
		
		
		
		
		complaintsService.saveOrUpdate(category);

			
		
		
		
		//get parent complaints
		List<ComplaintsReason> complaints = complaintsService.listByStore(language);
		model.addAttribute("complaints", complaints);
		

		model.addAttribute("success","success");
		return "complaints-reason";
	}
	
	
	//category list
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/complaints.html", method=RequestMethod.GET)
	public String displaycomplaints(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		setMenu(model,request);
		
		//does nothing, ajax subsequent request
		
		
		return "catalogue-complaints";
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/Customercomplaints.html", method=RequestMethod.GET)
	public String displayCustomerComplaints(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("complaints", "complaints");
		activeMenus.put("customer-complaints", "customer-complaints");
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("Complaints");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		
		
		return "customer-complaints";
	}
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/paging.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String pagecomplaints(HttpServletRequest request, HttpServletResponse response) {
		String categoryName = request.getParameter("name");
		String categoryCode = request.getParameter("code");


		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Language language = (Language)request.getAttribute("LANGUAGE");
				
		
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<ComplaintsReason> complaints = null;
					
			if(!StringUtils.isBlank(categoryName)) {
				
				
				complaints = complaintsService.getByName( categoryName, language);
				
			} else if(!StringUtils.isBlank(categoryCode)) {
				
				//categoryService.listByCodes(store, new ArrayList<String>(Arrays.asList(categoryCode)), language);
			
			} else {
				
				complaints = complaintsService.listByStore( language);
				
			}
					
					
			
			for(ComplaintsReason category : complaints) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", category.getId());
				
				
				
				entry.put("name", category.getEnglishName());
				entry.put("nameAr", category.getArabicName());
				entry.put("available", category.isAvailable());
				resp.addDataEntry(entry);
				
				
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging complaints", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		response.setCharacterEncoding("UTF-8");
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/customercomplaints/paging.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String pageCustomerComplaints(HttpServletRequest request, HttpServletResponse response) {
		String note = request.getParameter("note");
		String categoryCode = request.getParameter("status");
        String customerMail =request.getParameter("email");
        String reason =request.getParameter("complaintReason");
        String date=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			
			
			List<CustomerComplaint> complaints = null;
					
			if(!StringUtils.isBlank(note)||!StringUtils.isBlank(categoryCode) || !StringUtils.isBlank(customerMail) || !StringUtils.isBlank(reason)|| !StringUtils.isBlank(date)) {
				
				
				complaints = customerComplaintsService.getByName( note,request.getParameter("status"),customerMail,reason,date,endDate);
				
			} else if(!StringUtils.isBlank(categoryCode)) {
				
				//categoryService.listByCodes(store, new ArrayList<String>(Arrays.asList(categoryCode)), language);
			
			} else {
				
				complaints = customerComplaintsService.listByStore();
				
			}
					
					
			
			for(CustomerComplaint customerComplaint : complaints) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", customerComplaint.getId());
				
				
				
				entry.put("note", customerComplaint.getNotes());
				entry.put("email", customerComplaint.getCustomer().getEmailAddress());
				entry.put("complaintReason", customerComplaint.getCustomerComplaintReason().getEnglishName());
				entry.put("status", customerComplaint.getStatus());
				entry.put("complaintDate", DateUtil.formatDate(customerComplaint.getComplaintsDate()) );
				entry.put("customer", customerComplaint.getCustomer().getBilling().getFirstName());
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

	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value = "/admin/customercomplaints/remove.html", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String deleteComplaints(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		AjaxResponse resp = new AjaxResponse();

		try {

			Long id = Long.parseLong(sid);

			CustomerComplaint customerComplaint = new CustomerComplaint();
			customerComplaint.setId(id);

			customerComplaintsService.delete(customerComplaint);
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while deleting category", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}

		String returnString = resp.toJSONString();

		return returnString;
	}
	
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/complaints/remove.html", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public @ResponseBody String deleteCategory(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Long id = Long.parseLong(sid);
			
			ComplaintsReason category = complaintsService.getById(id);
			
			if(category==null  ) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				
			} else {
				complaintsService.delete(category);
				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
			}
		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting category", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	
	
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		
		activeMenus.put("complaints", "complaints");
		activeMenus.put("catalogue-complaints", "catalogue-complaints");
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("Complaints");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
