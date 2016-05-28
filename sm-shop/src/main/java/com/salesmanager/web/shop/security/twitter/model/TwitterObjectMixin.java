package com.salesmanager.web.shop.security.twitter.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Annotated mixin to add Jackson annotations to TwitterObject. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class TwitterObjectMixin {

	@JsonAnySetter
	abstract void add(String key, Object value);

}
