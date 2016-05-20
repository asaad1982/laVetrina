/**
 * 
 */
package com.salesmanager.core.modules.integration.shipping.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class RateModel implements Serializable {

	
private String	QuoteId;
private String CarrierId;
private String CarrierName;
public String getQuoteId() {
	return QuoteId;
}
public void setQuoteId(String quoteId) {
	QuoteId = quoteId;
}
public String getCarrierId() {
	return CarrierId;
}
public void setCarrierId(String carrierId) {
	CarrierId = carrierId;
}
public String getCarrierName() {
	return CarrierName;
}
public void setCarrierName(String carrierName) {
	CarrierName = carrierName;
}
public String getDeliveryType() {
	return DeliveryType;
}
public void setDeliveryType(String deliveryType) {
	DeliveryType = deliveryType;
}
public String getCost() {
	return Cost;
}
public void setCost(String cost) {
	Cost = cost;
}
public String getServiceStandard() {
	return ServiceStandard;
}
public void setServiceStandard(String serviceStandard) {
	ServiceStandard = serviceStandard;
}
public String getComments() {
	return Comments;
}
public void setComments(String comments) {
	Comments = comments;
}
public String getRoute() {
	return Route;
}
public void setRoute(String route) {
	Route = route;
}
public boolean isIsRuralDelivery() {
	return IsRuralDelivery;
}
public void setIsRuralDelivery(boolean isRuralDelivery) {
	IsRuralDelivery = isRuralDelivery;
}
public boolean isIsSaturdayDelivery() {
	return IsSaturdayDelivery;
}
public void setIsSaturdayDelivery(boolean isSaturdayDelivery) {
	IsSaturdayDelivery = isSaturdayDelivery;
}
public boolean isIsFreightForward() {
	return IsFreightForward;
}
public void setIsFreightForward(boolean isFreightForward) {
	IsFreightForward = isFreightForward;
}
public String getCarrierServiceType() {
	return CarrierServiceType;
}
public void setCarrierServiceType(String carrierServiceType) {
	CarrierServiceType = carrierServiceType;
}
public String getReason() {
	return Reason;
}
public void setReason(String reason) {
	Reason = reason;
}
public String getCarrier() {
	return Carrier;
}
public void setCarrier(String carrier) {
	Carrier = carrier;
}
private String DeliveryType;
private String Cost;
private String	ServiceStandard;
private String Comments;
private String Route;
private boolean IsRuralDelivery;
private boolean	IsSaturdayDelivery;
private boolean IsFreightForward;
private String	CarrierServiceType;
private String	Reason;
private String Carrier;

}
