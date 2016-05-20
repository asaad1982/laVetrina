package com.salesmanager.core.business.promo.model;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;

public class PromotionModel implements Serializable{

	private long promotionId;
	private String promotionName;
	private List<Product> products;
	private List<Manufacturer> brands;
	private BundlePromotion bundlePromotion;
	private CartPromotion cartPromotion;
	private UpSellingPromotion upSellingPromotion;
	public long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Manufacturer> getBrands() {
		return brands;
	}
	public void setBrands(List<Manufacturer> brands) {
		this.brands = brands;
	}
	public BundlePromotion getBundlePromotion() {
		return bundlePromotion;
	}
	public void setBundlePromotion(BundlePromotion bundlePromotion) {
		this.bundlePromotion = bundlePromotion;
	}
	public CartPromotion getCartPromotion() {
		return cartPromotion;
	}
	public void setCartPromotion(CartPromotion cartPromotion) {
		this.cartPromotion = cartPromotion;
	}
	public UpSellingPromotion getUpSellingPromotion() {
		return upSellingPromotion;
	}
	public void setUpSellingPromotion(UpSellingPromotion upSellingPromotion) {
		this.upSellingPromotion = upSellingPromotion;
	}
}
