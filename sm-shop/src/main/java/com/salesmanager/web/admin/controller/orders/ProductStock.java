package com.salesmanager.web.admin.controller.orders;

public class ProductStock {

	
	long id;
	String name;
	long  quantity;
	long  quantity_ordered;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getQuantity_ordered() {
		return quantity_ordered;
	}
	public void setQuantity_ordered(long quantity_ordered) {
		this.quantity_ordered = quantity_ordered;
	}
	
	
	
}
