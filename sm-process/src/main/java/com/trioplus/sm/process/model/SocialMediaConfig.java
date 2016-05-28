/**
 * 
 */
package com.trioplus.sm.process.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * @author Administrator
 *
 */
public class SocialMediaConfig  implements JSONAware  {

private String shareDiscountNumber ;
private String shareDiscountIntervalUnit ;
public Long getDiscountPercent() {
	return discountPercent;
}

public void setDiscountPercent(Long discountPercent) {
	this.discountPercent = discountPercent;
}


private String shareDiscountFrequency ;
private Long discountPercent;



public String getShareDiscountNumber() {
	return shareDiscountNumber;
}

public void setShareDiscountNumber(String shareDiscountNumber) {
	this.shareDiscountNumber = shareDiscountNumber;
}

public String getShareDiscountIntervalUnit() {
	return shareDiscountIntervalUnit;
}

public void setShareDiscountIntervalUnit(String shareDiscountIntervalUnit) {
	this.shareDiscountIntervalUnit = shareDiscountIntervalUnit;
}

public String getShareDiscountFrequency() {
	return shareDiscountFrequency;
}

public void setShareDiscountFrequency(String shareDiscountFrequency) {
	this.shareDiscountFrequency = shareDiscountFrequency;
}


public String toJSONString() {
	JSONObject data = new JSONObject();
	data.put("shareDiscountNumber", this.getShareDiscountNumber());
	data.put("shareDiscountIntervalUnit", this.getShareDiscountIntervalUnit());
	data.put("shareDiscountFrequency", this.getShareDiscountFrequency());
	data.put("discountPercent", this.getDiscountPercent());
	return data.toJSONString();
}




}
