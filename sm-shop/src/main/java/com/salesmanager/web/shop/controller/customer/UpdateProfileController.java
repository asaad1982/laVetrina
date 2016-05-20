package com.salesmanager.web.shop.controller.customer;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerGender;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.business.system.service.EmailService;
import com.salesmanager.core.utils.CoreConfiguration;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.shop.controller.AbstractController;
import com.salesmanager.web.shop.controller.customer.facade.CustomerFacade;
import com.salesmanager.web.shop.controller.customer.form.CustomerProfileForm;
import com.salesmanager.web.utils.LabelUtils;

/**
 * Registration of a new customer
 * @author Carl Samson
 *
 */

@SuppressWarnings( "deprecation" )
// http://stackoverflow.com/questions/17444258/how-to-use-new-passwordencoder-from-spring-security
@Controller
@RequestMapping("/shop/customer")
public class UpdateProfileController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProfileController.class);


	@Autowired
	private CoreConfiguration coreConfiguration;

	@Autowired
	private LanguageService languageService;


	@Autowired
	private CountryService countryService;


	@Autowired
	private ZoneService zoneService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailService emailService;

	@Autowired
	private LabelUtils messages;

	@Autowired
	private CustomerFacade customerFacade;


	@RequestMapping(value="/updateProfile.html", method=RequestMethod.GET)
	public String viewProfilePage(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {


		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
		Language language = super.getLanguage(request);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = null;
		if(auth != null &&
				request.isUserInRole("AUTH_CUSTOMER")) {
			customer = customerFacade.getCustomerByEmail(auth.getName(), store);

		} else {
			response.sendError(401, "Customer not authenticated");
			return null;
		}

		CustomerProfileForm form  = new CustomerProfileForm();
		
		Map<String,Country> countriesMap = countryService.getCountriesMap(language);
		Country country = countriesMap.get(customer.getBilling().getCountry().getIsoCode());
		List<Zone> zones = zoneService.getZones(country, language);
		Zone zone = null;
		if(zones!=null && zones.size()>0) {
			for(Zone z : zones) {
				if(z.getCode()!=null && z.getCode().equalsIgnoreCase(customer.getBilling().getZone().getCode()))
					zone=z;
			}
		}
		request.setAttribute("zoneSelected", zone.getCode());
		
		form.setFirstName(customer.getBilling().getFirstName());
		form.setLastName(customer.getBilling().getLastName());
		form.setCountry(country.getIsoCode());
		form.setZone(zone.getCode());
		form.setAddress(customer.getBilling().getAddress());

		
		form.setEmailAddress(customer.getEmailAddress());
		form.setPassword(customer.getPassword());
		form.setGender(customer.getGender()==CustomerGender.M?"M":"F");
//        customerModel.setDateOfBirth(new Date(customer.getBirthdate()));
        
		
		model.addAttribute("customer", form);

		return "updateProfile.entry";


	}

	@RequestMapping( value = "/updateProfile.html", method = RequestMethod.POST )
	public String updateProfile( @Valid
			@ModelAttribute("customer") CustomerProfileForm customer, BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpServletResponse response, final Locale locale, RedirectAttributes redirectAttributes )
					throws Exception
	{
		MerchantStore merchantStore = (MerchantStore) request.getAttribute( Constants.MERCHANT_STORE );
		Language language = super.getLanguage(request);

		if ( bindingResult.hasErrors() )
		{
			LOGGER.debug( "found {} validation error while validating in customer registration ",
					bindingResult.getErrorCount() );

			return "updateProfile.entry";

		}

		Map<String,Country> countriesMap = countryService.getCountriesMap(language);
		Country country = countriesMap.get(customer.getCountry());
		List<Zone> zones = zoneService.getZones(country, language);
		Zone zone = null;
		if(zones!=null && zones.size()>0) {
			for(Zone z : zones) {
				if(z.getCode()!=null && z.getCode().equalsIgnoreCase(customer.getZone()))
					zone=z;
			}
		}
		
		Customer customerData = new Customer();
		
		Billing billing = new Billing();
		billing.setFirstName(customer.getFirstName());
		billing.setLastName(customer.getLastName());
		billing.setCountry(country);
		billing.setZone(zone);
		billing.setAddress(customer.getAddress());

		customerData.setBilling(billing);
		
		customerData.setEmailAddress(customer.getEmailAddress());
		customerData.setPassword(passwordEncoder.encodePassword(customer.getPassword(),null));
		customerData.setGender(customer.getGender().equals("M")?CustomerGender.M:CustomerGender.F);
//        customerModel.setDateOfBirth(new Date(customer.getBirthdate()));
        
		customerData.setDefaultLanguage(language);
		customerData.setMerchantStore(merchantStore);
        
		customerFacade.updateCustomer(customerData);

		
		redirectAttributes.addFlashAttribute("successMsgCode", "message.updateProfile.success");
		
		return "redirect:/shop/customer/updateProfile.html";

	}


	@ModelAttribute("countryList")
	public List<Country> getCountries(final HttpServletRequest request){

		Language language = (Language) request.getAttribute( "LANGUAGE" );
		try
		{
			if ( language == null )
			{
				language = (Language) request.getAttribute( "LANGUAGE" );
			}

			if ( language == null )
			{
				language = languageService.getByCode( Constants.DEFAULT_LANGUAGE );
			}

			List<Country> countryList=countryService.getCountries( language );
			return countryList;
		}
		catch ( ServiceException e )
		{
			LOGGER.error( "Error while fetching country list ", e );

		}
		return Collections.emptyList();
	}

	@ModelAttribute("zoneList")
	public List<Zone> getZones(final HttpServletRequest request){
		return zoneService.list();
	}


}
