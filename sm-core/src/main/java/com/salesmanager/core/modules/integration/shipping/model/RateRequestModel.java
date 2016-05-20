package com.salesmanager.core.modules.integration.shipping.model;

import java.io.Serializable;
import java.util.List;

public class RateRequestModel implements Serializable{
	private String origin; 
	private DestinationModel destination ;
	private List<Package> packages ;
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public DestinationModel getDestination() {
		return destination;
	}
	public void setDestination(DestinationModel destination) {
		this.destination = destination;
	}
	public List<Package> getPackages() {
		return packages;
	}
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	public boolean isIssaturdaydelivery() {
		return issaturdaydelivery;
	}
	public void setIssaturdaydelivery(boolean issaturdaydelivery) {
		this.issaturdaydelivery = issaturdaydelivery;
	}
	public boolean isIssignaturerequired() {
		return issignaturerequired;
	}
	public void setIssignaturerequired(boolean issignaturerequired) {
		this.issignaturerequired = issignaturerequired;
	}
	public boolean isDutiesandtaxesbyreceiver() {
		return dutiesandtaxesbyreceiver;
	}
	public void setDutiesandtaxesbyreceiver(boolean dutiesandtaxesbyreceiver) {
		this.dutiesandtaxesbyreceiver = dutiesandtaxesbyreceiver;
	}
	public boolean isRuraloverride() {
		return ruraloverride;
	}
	public void setRuraloverride(boolean ruraloverride) {
		this.ruraloverride = ruraloverride;
	}
	public String getDeliveryreference() {
		return deliveryreference;
	}
	public void setDeliveryreference(String deliveryreference) {
		this.deliveryreference = deliveryreference;
	}
	private boolean issaturdaydelivery;
	private boolean issignaturerequired;
	private boolean dutiesandtaxesbyreceiver;
	private boolean ruraloverride;
	private String  deliveryreference;
	


}
