package com.salesmanager.core.business.promo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;
@Entity
@Table(name = "Bundle_Promotion", schema="lavetrina")
public class BundlePromotion extends SalesManagerEntity<Long, BundlePromotion> implements Serializable{
	@Id
	@Column(unique=true,nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "bundle_promotion_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Transient
	private List<ProductRelationship> productRelationships;
	
	public List<ProductRelationship> getProductRelationships() {
		return productRelationships;
	}

	public void setProductRelationships(
			List<ProductRelationship> productRelationships) {
		this.productRelationships = productRelationships;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="promotion_cart_id", nullable=true, updatable=true)
	private Promotion promotion;
	
	
	
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="relation_bundle_id", nullable=true, updatable=true)
   private ProductRelationship productRelationship;
	

	
	@Column(length=6)
	private double bundlePrice;
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public ProductRelationship getProductRelationship() {
		return productRelationship;
	}

	public void setProductRelationship(ProductRelationship productRelationship) {
		this.productRelationship = productRelationship;
	}

	public double getBundlePrice() {
		return bundlePrice;
	}

	public void setBundlePrice(double bundlePrice) {
		this.bundlePrice = bundlePrice;
	}
	
}