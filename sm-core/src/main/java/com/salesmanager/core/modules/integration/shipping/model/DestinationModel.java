/**
 * 
 */
package com.salesmanager.core.modules.integration.shipping.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class DestinationModel implements Serializable {

	private String Id;
	private String name; 
	private String Email;
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public boolean isIsrural() {
		return isrural;
	}
	public void setIsrural(boolean isrural) {
		this.isrural = isrural;
	}
	public String getDeliveryinstructions() {
		return deliveryinstructions;
	}
	public void setDeliveryinstructions(String deliveryinstructions) {
		this.deliveryinstructions = deliveryinstructions;
	}
	public boolean isSendtrackingemail() {
		return sendtrackingemail;
	}
	public void setSendtrackingemail(boolean sendtrackingemail) {
		this.sendtrackingemail = sendtrackingemail;
	}
	public int getCostcentreid() {
		return costcentreid;
	}
	public void setCostcentreid(int costcentreid) {
		this.costcentreid = costcentreid;
	}
	public boolean isExplicitnotrural() {
		return explicitnotrural;
	}
	public void setExplicitnotrural(boolean explicitnotrural) {
		this.explicitnotrural = explicitnotrural;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	private String contactperson;
	private String phonenumber;
	private boolean isrural;
	private String deliveryinstructions; 
	private  boolean sendtrackingemail; 
	private int costcentreid ;
	private boolean explicitnotrural;
	private Address address; 



}
