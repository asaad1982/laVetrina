package com.salesmanager.web.shop.controller.customer;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesmanager.core.business.customer.CustomerRegistrationException;
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
import com.salesmanager.web.admin.entity.userpassword.UserReset;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.customer.Address;
import com.salesmanager.web.entity.customer.AnonymousCustomer;
import com.salesmanager.web.entity.customer.CustomerEntity;
import com.salesmanager.web.entity.customer.PersistableCustomer;
import com.salesmanager.web.entity.customer.SecuredShopPersistableCustomer;
import com.salesmanager.web.shop.controller.AbstractController;
import com.salesmanager.web.shop.controller.customer.facade.CustomerFacade;
import com.salesmanager.web.shop.controller.customer.form.CustomerProfileForm;
import com.salesmanager.web.shop.controller.customer.form.ForgetPasswordForm;
import com.salesmanager.web.utils.EmailTemplatesUtils;
import com.salesmanager.web.utils.LabelUtils;
import com.salesmanager.core.business.customer.model.Customer;

/**
 * Registration of a new customer
 * @author Carl Samson
 *
 */

@SuppressWarnings( "deprecation" )
// http://stackoverflow.com/questions/17444258/how-to-use-new-passwordencoder-from-spring-security
@Controller
@RequestMapping("/shop/customer")
public class ForgetPasswordController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForgetPasswordController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LabelUtils messages;

	@Autowired
	private CustomerFacade customerFacade;

	@Autowired
	private EmailTemplatesUtils emailTemplatesUtils;
	
	@RequestMapping(value="/forgetPassword.html", method=RequestMethod.GET)
	public String viewProfilePage(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {


		model.addAttribute("customer", new ForgetPasswordForm());

		return "forgetPassword.entry";


	}

	@RequestMapping( value = "/forgetPassword.html", method = RequestMethod.POST )
	public String registerCustomer( @Valid
			@ModelAttribute("customer") ForgetPasswordForm customer, BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpServletResponse response, final Locale locale, RedirectAttributes redirectAttributes )
					throws Exception
	{
		MerchantStore merchantStore = (MerchantStore) request.getAttribute( Constants.MERCHANT_STORE );

		if ( StringUtils.isNotBlank( customer.getEmailAddress() ) )
		{
			if ( !customerFacade.checkIfUserExists( customer.getEmailAddress(), merchantStore ) )
			{
				LOGGER.debug( "Customer with username {} already exists for this store ", customer.getEmailAddress() );
				
				FieldError error = new FieldError("userName","userName",messages.getMessage("message.email.not.registered", locale));
				bindingResult.addError(error);
			}
		}

		if ( bindingResult.hasErrors() )
		{
			LOGGER.debug( "found {} validation error while validating in customer registration ",
					bindingResult.getErrorCount() );

			return "forgetPassword.entry";

		}

		String password = UserReset.generateRandomString();
		System.out.println("################################################password: " + password);
		String encodedPassword = passwordEncoder.encodePassword(password, null);
		
		Customer cust = customerFacade.getCustomerByEmail(customer.getEmailAddress(), merchantStore);
		cust.setPassword(encodedPassword);
		
		customerFacade.updateCustomer(cust);
		
		PersistableCustomer persistableCustomer = new PersistableCustomer();
        
        Address b = new Address();
        b.setFirstName(cust.getBilling().getFirstName());
        b.setLastName(cust.getBilling().getLastName());
        
        persistableCustomer.setBilling(b);
        persistableCustomer.setEmailAddress(customer.getEmailAddress());
        persistableCustomer.setUserName(cust.getBilling().getFirstName());
        persistableCustomer.setEncodedPassword(encodedPassword);
        //TODO add password to email body
        emailTemplatesUtils.sendRegistrationEmail( persistableCustomer, merchantStore, locale, request.getContextPath() );

		
		redirectAttributes.addFlashAttribute("successMsgCode", "message.forgetPassword.success");
		
		return "redirect:/shop/customer/forgetPassword.html";

	}


}
