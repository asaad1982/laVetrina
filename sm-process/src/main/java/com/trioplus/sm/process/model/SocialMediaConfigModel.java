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
public class SocialMediaConfigModel  implements JSONAware  {

private Long shareDiscountNumber ;
private Long shareDiscountIntervalUnit ;
public Integer getDiscountPercent() {
	return discountPercent;
}

public void setDiscountPercent(Integer discountPercent) {
	this.discountPercent = discountPercent;
}


private Long shareDiscountFrequency ;
private Integer discountPercent;



public Long getShareDiscountNumber() {
	return shareDiscountNumber;
}

public void setShareDiscountNumber(Long shareDiscountNumber) {
	this.shareDiscountNumber = shareDiscountNumber;
}

public Long getShareDiscountIntervalUnit() {
	return shareDiscountIntervalUnit;
}

public void setShareDiscountIntervalUnit(Long shareDiscountIntervalUnit) {
	this.shareDiscountIntervalUnit = shareDiscountIntervalUnit;
}

public Long getShareDiscountFrequency() {
	return shareDiscountFrequency;
}

public void setShareDiscountFrequency(Long shareDiscountFrequency) {
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
