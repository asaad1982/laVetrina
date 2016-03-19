package com.salesmanager.core.business.complaint.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.NotEmpty;


import com.salesmanager.core.business.generic.model.SalesManagerEntity;

@Entity(name="ComplaintsReason")

@Table(name = "customer_complaint_reasons", schema= "lavetrina" )
public class ComplaintsReason extends SalesManagerEntity<Long, ComplaintsReason> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3104091800104147576L;


	@Id
	@Column(name = "customer_complaint_reason_id", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "COMPLAINTSREASON_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	

	@NotEmpty
	@Column(name="name_en", length=100, nullable=false)
	private String englishName;
	
	@NotEmpty
	@Column(name="name_ar", length=100, nullable=false)
	private String arabicName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getArabicName() {
		return arabicName;
	}

	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
	}
}
