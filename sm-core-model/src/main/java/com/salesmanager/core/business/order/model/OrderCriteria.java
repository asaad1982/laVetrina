package com.salesmanager.core.business.order.model;

import com.salesmanager.core.business.common.model.Criteria;

public class OrderCriteria extends Criteria {
	
	private String customerName;
	private String paymentMethod;
	private Long customerId;
	private String  customerEmailAddress;
	
	
	private String	orderId ;
 
	private String	date ;
	private String	country ;
	private String	status ;
	
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
 
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}
	public void setCustomerEmailAddress(String customerEmailAddress) {
		this.customerEmailAddress = customerEmailAddress;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerName() {
		return customerName;
	}
    public Long getCustomerId()
    {
        return customerId;
    }
    public void setCustomerId( Long customerId )
    {
        this.customerId = customerId;
    }
   
	
	
	

}
