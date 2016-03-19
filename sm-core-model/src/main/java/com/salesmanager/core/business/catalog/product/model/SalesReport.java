package com.salesmanager.core.business.catalog.product.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SalesReport {
	public String getProductSKU() {
		return productSKU;
	}
	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	public BigInteger getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(BigInteger productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	private int month;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	private String productSKU;
	private String productName;
	private BigDecimal totalSales;
	private BigInteger productCategoryId;
	private String productCategoryName;

}
