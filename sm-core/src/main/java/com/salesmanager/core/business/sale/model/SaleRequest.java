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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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

	@Id
	@Column(name="REQUEST_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
	pkColumnValue = "SALE_REQUEST_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")

	private Long id;
	
	@NotEmpty(message="{validation.wholeSale.customerEmail.required}")
	@Email(message="{validation.wholeSale.customerEmail.invalidFormat}")
	@Column(name="CUSTOMER_EMAIL",unique=true,length=100)
	private String customerEmail;
	
	//TODO make index
	@NotNull(message="{validation.wholeSale.productId.required}")
	@Column(name="PRODUCT_ID")
	private Long productId;
	
	@NotNull(message="{validation.wholeSale.quantity.required}")
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@NotEmpty(message="{validation.wholeSale.customerName.required}")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "{validation.wholeSale.customerName.invalidFormat}")
	@Size(max=100, message="{validation.wholeSale.customerName.size}")
	@Column(name="CUSTOMER_NAME",length=100)
	private String customerName;
	
	//TODO make index
	@NotEmpty(message="{validation.wholeSale.customerMobile.required}")
	@Pattern(regexp = "^[\\d]*$", message = "{validation.wholeSale.customerMobile.invalidFormat}")
	@Column(name="CUSTOMER_MOBILE",length=100)
	private String customerMobile;
	
	//TODO Id will be generated date.long
	@Column(name="REQUEST_NUMBER",length=100)
	private String requestNumber;
	
	//TODO To handle update request if required
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

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
