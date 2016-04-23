package com.salesmanager.core.business.sale.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.common.model.audit.AuditListener;
import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.common.model.audit.Auditable;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;
import com.salesmanager.core.business.reference.zone.model.Zone;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SALE_REQUEST", schema="lavetrina")
public class SaleRequest extends SalesManagerEntity<Long, Zone> implements Auditable {
	
	private static final long serialVersionUID = 1L;

	public SaleRequest() {
		
	}

	//TODO Id will be generated date.long
	@Id
	@Column(name="REQUEST_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
	pkColumnValue = "SALE_REQUEST_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")

	private Long id;
	
	@NotEmpty
	@Email
	@Column(name="CUSTOMER_EMAIL",unique=true,length=100)
	private String customerEmail;
	
	//TODO make index
	@Column(name="PRODUCT_ID")
	private Product product;
	
	//TODO make index
	@Column(name="CUSTOMER_ID")
	private Long customerId;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="CUSTOMER_NAME",length=100)
	private String customerName;
	
	//TODO make index
	//TODO validate mobile format
//	@NumberFormat(pattern="")
	@Column(name="CUSTOMER_MOBILE",length=100)
	private String customerMobile;
	
	
	@Column(name="REQUEST_NUMBER",length=100)
	private String requestNumber;
	
	
	//TODO To handle update request if required
	@Embedded
	private AuditSection auditSection = new AuditSection();
	

	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		auditSection = audit;
		
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}


}
