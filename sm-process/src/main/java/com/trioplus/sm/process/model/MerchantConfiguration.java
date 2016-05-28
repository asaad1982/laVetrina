package com.trioplus.sm.process.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;


/**
 * Merchant configuration information
 * @author Carl Samson
 *
 */
@Entity

@Table(name = "MERCHANT_CONFIGURATION", schema= "lavetrina", uniqueConstraints=
@UniqueConstraint(columnNames = {"MERCHANT_CONFIG_ID", "CONFIG_KEY"}))
public class MerchantConfiguration  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4246917986731953459L;

	@Id
	@Column(name = "MERCHANT_CONFIG_ID")
	private Long id;

	
	@Column(name="CONFIG_KEY")
	private String key;

	
	@Column(name="VALUE")
	@Type(type = "org.hibernate.type.StringClobType")
	private String value;
	
	
	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}



	
}
