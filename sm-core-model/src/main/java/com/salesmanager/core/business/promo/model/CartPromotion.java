package com.salesmanager.core.business.promo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;
import com.salesmanager.core.business.reference.country.model.Country;
@Entity
@Table(name="cart_promotion")
public class CartPromotion extends SalesManagerEntity<Long, CartPromotion> implements Serializable{
	@Id
	@Column(unique=true,nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "cart_promotion_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="promotion_cart_id", nullable=true, updatable=true)
	private Promotion promotion;
	
	
	
	
	
	@Column(length=8)
	private String couponCode;
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public long getCouponDiscountType() {
		return couponDiscountType;
	}

	public void setCouponDiscountType(long couponDiscountType) {
		this.couponDiscountType = couponDiscountType;
	}

	public double getCouponDiscountAmount() {
		return couponDiscountAmount;
	}

	public void setCouponDiscountAmount(double couponDiscountAmount) {
		this.couponDiscountAmount = couponDiscountAmount;
	}

	public boolean isFreeShippingOption() {
		return freeShippingOption;
	}

	public void setFreeShippingOption(boolean freeShippingOption) {
		this.freeShippingOption = freeShippingOption;
	}

	public String getEmailMessageEn() {
		return emailMessageEn;
	}

	public void setEmailMessageEn(String emailMessageEn) {
		this.emailMessageEn = emailMessageEn;
	}

	public String getEmailMessageAr() {
		return emailMessageAr;
	}

	public void setEmailMessageAr(String emailMessageAr) {
		this.emailMessageAr = emailMessageAr;
	}






	@Column
	private long couponDiscountType;
	@Column(length=6)
	private double couponDiscountAmount;
	@Column
	private boolean freeShippingOption;
	@Column
	private String emailMessageEn;
	@Column
	private String emailMessageAr;

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	
	
}
