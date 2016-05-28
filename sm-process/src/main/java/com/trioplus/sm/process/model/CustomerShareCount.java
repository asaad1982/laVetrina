/**
 * 
 */
package com.trioplus.sm.process.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="CUSTOMER_SHARE_VIEW")
public class CustomerShareCount implements Serializable {

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}
@Id
@Column(name="CUSTOMER_ID")
	private Long id;
	

@Column(name="count")
private Long count;



}
