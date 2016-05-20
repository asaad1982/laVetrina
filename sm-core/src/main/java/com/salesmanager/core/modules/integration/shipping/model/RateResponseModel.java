/**
 * 
 */
package com.salesmanager.core.modules.integration.shipping.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class RateResponseModel implements Serializable  {

private List<RateModel> Available;
public List<RateModel> getAvailable() {
	return Available;
}
public void setAvailable(List<RateModel> available) {
	Available = available;
}
public List<RateModel> getRejected() {
	return Rejected;
}
public void setRejected(List<RateModel> rejected) {
	Rejected = rejected;
}
public List<ValidationErrorModel> getValidationErrors() {
	return ValidationErrors;
}
public void setValidationErrors(List<ValidationErrorModel> validationErrors) {
	ValidationErrors = validationErrors;
}
private List<RateModel> Rejected;
private List<ValidationErrorModel> ValidationErrors;

}
