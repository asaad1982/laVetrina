package com.salesmanager.web.entity.catalog.manufacturer;

import java.io.Serializable;

public class ReadableManufacturer extends ManufacturerEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManufacturerDescription description;
	private int productCount;
	
	public void setDescription(ManufacturerDescription description) {
		this.description = description;
	}
	public ManufacturerDescription getDescription() {
		return description;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

}
