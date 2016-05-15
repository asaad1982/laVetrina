package com.salesmanager.core.business.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.promo.model.BundlePromotion;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promo.model.UpSellingPromotion;
import com.salesmanager.core.business.promotion.dao.BounsDao;
import com.salesmanager.core.business.promotion.dao.BundlePromotionDAO;
import com.salesmanager.core.business.promotion.dao.UpSellingPromotionDAO;
@Service("upSellingPromotionService")
public class UpSellingServiceImpl extends SalesManagerEntityServiceImpl<Long, UpSellingPromotion> implements UpSellingPromotionService {
	UpSellingPromotionDAO upSellingPromotionDAO;
	@Autowired
	public UpSellingServiceImpl(UpSellingPromotionDAO genericDao) {
		super(genericDao);
		this.upSellingPromotionDAO=genericDao;
	}
	@Override
	public void saveOrUpdate(UpSellingPromotion upSellingPromotion)
			throws ServiceException {
		if(upSellingPromotion.getId()!=null && upSellingPromotion.getId()>0) {

			super.update(upSellingPromotion);
			
		} else {
			Promotion promotion=upSellingPromotion.getPromotion();
			List<Product> products=upSellingPromotion.getProducts();
			upSellingPromotion.setPromotion(null);
			upSellingPromotion.setProducts(null);
			super.save(upSellingPromotion);
			
			upSellingPromotion.setPromotion(promotion);
			upSellingPromotion.setProducts(products);
			super.update(upSellingPromotion);
			
			
		}
		
	}

	

}
