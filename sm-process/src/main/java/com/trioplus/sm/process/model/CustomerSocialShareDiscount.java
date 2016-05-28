/**
 * 
 */
package com.trioplus.sm.process.model;

/**
 * @author Administrator
 *
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="CUSTOMER_SOCIAL_DISCOUNT", uniqueConstraints=
@UniqueConstraint(columnNames = {"CUSTOMER_ID", "COUPON"}))

public class CustomerSocialShareDiscount {

	/**
	 * 
	 */
	@Column(name="CUSTOMER_ID")
	private Long customerId;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getCouponStartDate() {
		return couponStartDate;
	}
	public void setCouponStartDate(Date couponStartDate) {
		this.couponStartDate = couponStartDate;
	}
	public Date getCouponEndDate() {
		return couponEndDate;
	}
	public void setCouponEndDate(Date couponEndDate) {
		this.couponEndDate = couponEndDate;
	}
	@Column(name="COUPON")
	private String coupon;
	@Column(name="CUSTOMER_ID")
	private Date creationDate;
	@Column(name="START_DATE")
	private Date couponStartDate;
	@Column(name="END_DATE")
	private Date couponEndDate;
	
	
}
