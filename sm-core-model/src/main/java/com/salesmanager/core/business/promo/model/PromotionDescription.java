package com.salesmanager.core.business.promo.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import com.salesmanager.core.business.generic.model.SalesManagerEntity;

import java.math.BigInteger;


/**
 * The persistent class for the promotion_description database table.
 * 
 */
@Entity
@Table(name="promotion_description")
@NamedQuery(name="PromotionDescription.findAll", query="SELECT p FROM PromotionDescription p")
public class PromotionDescription extends SalesManagerEntity<Long, PromotionDescription> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true,nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PromotionDescription_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@Lob
	private String description;

	@Column(name="language_id")
	private int languageId;
	@Column(unique=true,nullable=false)
	@NotEmpty	
	private String name;

	//bi-directional many-to-one association to Promotion
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
	
	@Transient
	private String languageName;

	public PromotionDescription() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}