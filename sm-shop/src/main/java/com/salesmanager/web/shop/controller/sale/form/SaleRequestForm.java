package com.salesmanager.web.shop.controller.sale.form;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SaleRequestForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="{validation.wholeSale.customerEmail.required}")
	@Email(message="{validation.wholeSale.customerEmail.invalidFormat}")
	private String customerEmail;
	
	@NotEmpty(message="{validation.wholeSale.customerName.required}")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "{validation.wholeSale.customerName.invalidFormat}")
	@Size(max=100, message="{validation.wholeSale.customerName.size}")
	private String customerName;
	
	@NotEmpty(message="{validation.wholeSale.customerMobile.required}")
	@Pattern(regexp = "^[\\d]*$", message = "{validation.wholeSale.customerMobile.invalidFormat}")
	private String customerMobile;
	
	
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}


}
