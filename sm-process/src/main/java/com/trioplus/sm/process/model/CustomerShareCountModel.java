/**
 * 
 */
package com.trioplus.sm.process.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="CUSTOMER_SHARE_VIEW")
public class CustomerShareCountModel implements Serializable {

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}
@Id
@Column(name="CUSTOMER_ID")
	private Long id;
	

public String getCustomerEmail() {
	return customerEmail;
}


public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
}
@Column(name="count")
private Long count;

@Column(name="CUSTOMER_EMAIL")
private String customerEmail;



}
