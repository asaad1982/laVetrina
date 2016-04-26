package com.salesmanager.core.business.sale.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.business.common.model.audit.AuditListener;
import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.common.model.audit.Auditable;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SALE_REQUEST", schema="lavetrina")
public class SaleRequest extends SalesManagerEntity<Long, SaleRequest> implements Auditable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REQUEST_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
	pkColumnValue = "SALE_REQUEST_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Column(name="CUSTOMER_EMAIL",length=100)
	private String customerEmail;
	
	@Column(name="CUSTOMER_NAME",length=100)
	private String customerName;
	
	//TODO make index
	@Column(name="CUSTOMER_MOBILE",length=100)
	private String customerMobile;
	
	//TODO make index
	@Column(name="REQUEST_NUMBER",length=100)
	private String requestNumber;
	
	//TODO To handle update request if required
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@OneToMany(mappedBy="saleRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductSaleRequest> productSaleRequests = new ArrayList<ProductSaleRequest>();
	
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
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

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public List<ProductSaleRequest> getProductSaleRequests() {
		return productSaleRequests;
	}

	public void setProductSaleRequests(List<ProductSaleRequest> productSaleRequests) {
		this.productSaleRequests = productSaleRequests;
	}


}
