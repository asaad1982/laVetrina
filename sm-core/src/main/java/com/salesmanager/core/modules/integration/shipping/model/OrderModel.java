/**
 * 
 */
package com.salesmanager.core.modules.integration.shipping.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class OrderModel implements Serializable{
  /**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String packingslipno;
  public String getPackingslipno() {
	return packingslipno;
}
public void setPackingslipno(String packingslipno) {
	this.packingslipno = packingslipno;
}
public String getConsignee() {
	return consignee;
}
public void setConsignee(String consignee) {
	this.consignee = consignee;
}

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getTicketnumber() {
	return ticketnumber;
}
public void setTicketnumber(String ticketnumber) {
	this.ticketnumber = ticketnumber;
}
public String getTrackingurl() {
	return trackingurl;
}
public void setTrackingurl(String trackingurl) {
	this.trackingurl = trackingurl;
}
public String getPicked() {
	return Picked;
}
public void setPicked(String picked) {
	Picked = picked;
}
public String getDelivered() {
	return Delivered;
}
public void setDelivered(String delivered) {
	Delivered = delivered;
	
}
public String getAddress1() {
	return address1;
}
public void setAddress1(String address1) {
	this.address1 = address1;
}
public String getAddress2() {
	return address2;
}
public void setAddress2(String address2) {
	this.address2 = address2;
}
public String getSuburb() {
	return suburb;
}
public void setSuburb(String suburb) {
	this.suburb = suburb;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getPostcode() {
	return postcode;
}
public void setPostcode(String postcode) {
	this.postcode = postcode;
}
public String getDelvinstructions() {
	return delvinstructions;
}
public void setDelvinstructions(String delvinstructions) {
	this.delvinstructions = delvinstructions;
}
public String getDelvref() {
	return delvref;
}
public void setDelvref(String delvref) {
	this.delvref = delvref;
}
public String getContactname() {
	return contactname;
}
public void setContactname(String contactname) {
	this.contactname = contactname;
}
public String getContactphone() {
	return contactphone;
}
public void setContactphone(String contactphone) {
	this.contactphone = contactphone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
private String consignee;
  private String status;
  private String ticketnumber;
  private String trackingurl;
  private String  Picked;
  private String Delivered;
  private String address1;
  private String address2;
  private String  suburb;
  private String  country;
  private String city;
  private String postcode;
  private String delvinstructions;
  private String delvref;
  private String  contactname;
  private String  contactphone;
  private String  email;
  



}
