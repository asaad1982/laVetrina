package com.salesmanager.web.shop.controller.sale.form;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SaleRequestForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="{NotEmpty.saleRequestForm.customerEmail}")
	@Email(message="{validation.wholeSale.customerEmail.invalidFormat}")
	private String customerEmail;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.customerName}")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "{validation.wholeSale.customerName.invalidFormat}")
	@Size(max=100, message="{validation.wholeSale.customerName.size}")
	private String customerName;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.customerMobile}")
	@Pattern(regexp = "^[\\d]*$", message = "{validation.wholeSale.customerMobile.invalidFormat}")
	@Size(min=11, max=11, message="{validation.wholeSale.customerMobile.size}")
	private String customerMobile;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.messageBody}")
	@Size(max=300, message="{validation.wholeSale.messageBody.size}")
	@Pattern(regexp = "^[0-9a-zA-Z\\s,\\-\\._]*$", message = "{validation.wholeSale.messageBody.invalidFormat}")
	private String messageBody;
	
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

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}


}
