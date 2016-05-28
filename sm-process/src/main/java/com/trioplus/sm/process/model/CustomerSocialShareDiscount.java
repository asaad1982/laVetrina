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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="CUSTOMER_SOCIAL_DISCOUNT_HISTORY")

public class CustomerSocialShareDiscount {

	/**
	 * 
	 */
	@Id
	@Column(name = "TRANSACTION_ID", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transactionID;
	@Column(name="DISCOUNT_PERCENTAGE")
	private Integer discountPercentage;
	
	
	
	public Long getTransactionID() {
		return transactionID;
	}
	public Integer getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
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
	@Column(name="CREATION_DATE")
	private Date creationDate;
	@Column(name="START_DATE")
	private Date couponStartDate;
	@Column(name="END_DATE")
	private Date couponEndDate;
	
	
}
