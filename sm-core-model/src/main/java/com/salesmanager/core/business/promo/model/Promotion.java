package com.salesmanager.core.business.promo.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.salesmanager.core.business.generic.model.SalesManagerEntity;
import com.salesmanager.core.modules.constants.Constants;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the promotion database table.
 * 
 */
@Entity
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM Promotion p")
public class Promotion extends SalesManagerEntity<Long, Promotion> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true,nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "Promotion_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date endate;

	
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date startDate;
	@NotEmpty
	private String status;

	//bi-directional many-to-one association to PromotionDescription
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy="promotion")
	private List<PromotionDescription> promotionDescriptions;
	@OneToOne
	@JoinColumn(name="promotion_type_id")
	private PromotionType promotionType;
	@OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="promotion_rule_id")
	private PromotionRule promotionRule;
	
	
	
	
	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	private double promotionVal;

	public double getPromotionVal() {
		return promotionVal;
	}

	public void setPromotionVal(double promotionVal) {
		this.promotionVal = promotionVal;
	}

	public Promotion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEndate() {
		return this.endate;
	}

	public void setEndate(Date endate) {
		this.endate = endate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PromotionDescription> getPromotionDescriptions() {
		return this.promotionDescriptions;
	}

	public void setPromotionDescriptions(List<PromotionDescription> promotionDescriptions) {
		this.promotionDescriptions = promotionDescriptions;
	}

	public PromotionDescription addPromotionDescription(PromotionDescription promotionDescription) {
		getPromotionDescriptions().add(promotionDescription);
		promotionDescription.setPromotion(this);

		return promotionDescription;
	}

	public PromotionDescription removePromotionDescription(PromotionDescription promotionDescription) {
		getPromotionDescriptions().remove(promotionDescription);
		promotionDescription.setPromotion(null);

		return promotionDescription;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	

}