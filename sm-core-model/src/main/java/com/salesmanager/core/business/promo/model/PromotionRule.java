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

public class PromotionRule extends SalesManagerEntity<Long, PromotionRule> implements Serializable{
	@Id
	@Column(unique=true,nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PromotionRule_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Transient
	private String[] brandsId;
	
	public String[] getBrandsId() {
		return brandsId;
	}

	public void setBrandsId(String[] brandsId) {
		this.brandsId = brandsId;
	}
	
	@Transient
	private String[] categoriesId;
	
	
	public String[] getCategoriesId() {
		return categoriesId;
	}

	public void setCategoriesId(String[] categoriesId) {
		this.categoriesId = categoriesId;
	}
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinTable(name = "PROMOTION_CUSTOMER", schema="lavetrina", joinColumns = { 
			@JoinColumn(name = "PROMOTION_ID", nullable = false, updatable = false) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "CUSTOMER_ID", 
					nullable = false, updatable = false) })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	private int promotionTragetAge;

	public int getPromotionTragetAge() {
		return promotionTragetAge;
	}

	public void setPromotionTragetAge(int promotionTragetAge) {
		this.promotionTragetAge = promotionTragetAge;
	}
	
	private String targetGender;

	public String getTargetGender() {
		return targetGender;
	}

	public void setTargetGender(String targetGender) {
		this.targetGender = targetGender;
	}
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinTable(name = "PROMOTION_COUNTRIES", schema="lavetrina", joinColumns = { 
			@JoinColumn(name = "PROMOTION_ID", nullable = false, updatable = true,insertable=true) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "country_ID", 
					nullable = false, updatable = true,insertable=true) })
	@Fetch(value = FetchMode.SUBSELECT)
	@Cascade({
		org.hibernate.annotations.CascadeType.DETACH,
		org.hibernate.annotations.CascadeType.LOCK,
		org.hibernate.annotations.CascadeType.REFRESH,
		org.hibernate.annotations.CascadeType.REPLICATE
		
	})
	
	private List<Country> countries;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinTable(name = "PROMOTION_categories", schema="lavetrina", joinColumns = { 
			@JoinColumn(name = "PROMOTION_ID", nullable = false, updatable = false) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "Category_ID", 
					nullable = false, updatable = false) })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Category> categories;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinTable(name = "PROMOTION_BRANDS", schema="lavetrina", joinColumns = { 
			@JoinColumn(name = "PROMOTION_ID", nullable = false, updatable = false) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "Manufacturer_ID", 
					nullable = false, updatable = false) })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Manufacturer> brands;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinTable(name = "PROMOTION_Product", schema="lavetrina", joinColumns = { 
			@JoinColumn(name = "PROMOTION_ID", nullable = false, updatable = false) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID", 
					nullable = false, updatable = false) })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Product> products;

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Manufacturer> getBrands() {
		return brands;
	}

	public void setBrands(List<Manufacturer> brands) {
		this.brands = brands;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
