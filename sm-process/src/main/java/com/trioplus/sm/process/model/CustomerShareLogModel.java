/**
 * 
 */
package com.trioplus.sm.process.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author Administrator
 *
 */

@Entity
@Table(name="CUSTOMER_SHARE_LOG",schema="lavetrina")
public class CustomerShareLogModel  {

@Id
@Column(name = "TRANSACTION_ID", unique=true, nullable=false)
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
public Long getId() {
		return id;
	}
public void setId(Long id) {
		this.id = id;
	}
@Column(name="CUSTOMER_ID")
private long customerId;
public long getCustomerId() {
	return customerId;
}
public void setCustomerId(long customerId) {
	this.customerId = customerId;
}
public Date getTransactionDate() {
	return transactionDate;
}
public void setTransactionDate(Date transactionDate) {
	this.transactionDate = transactionDate;
}
public int getChannel() {
	return channel;
}
public boolean isDiscountSent() {
	return discountSent;
}
public void setDiscountSent(boolean discountSent) {
	this.discountSent = discountSent;
}
public void setChannel(int channel) {
	this.channel = channel;
}
@Column(name="TRANSACTION_DATE")
@DateTimeFormat(pattern = "dd/MM/yyyy")
private Date transactionDate;
@Column(name="CHANNEL")
private int channel;


@Column(name="DISCOUNT_SENT")
private boolean discountSent;



}
