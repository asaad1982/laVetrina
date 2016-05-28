package com.salesmanager.core.business.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.promo.model.BundlePromotion;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promotion.dao.BounsDao;
import com.salesmanager.core.business.promotion.dao.BundlePromotionDAO;
@Service("bundlePromotionService")
public class BundleServiceImpl extends SalesManagerEntityServiceImpl<Long, BundlePromotion> implements BundlePromotionService {
	BundlePromotionDAO bundlePromotionDAO;
	@Autowired
	public BundleServiceImpl(BundlePromotionDAO genericDao) {
		super(genericDao);
		this.bundlePromotionDAO=genericDao;
	}
	@Override
	public void saveOrUpdate(BundlePromotion bundlePromotion)
			throws ServiceException {
		if(bundlePromotion.getId()!=null && bundlePromotion.getId()>0) {

			super.update(bundlePromotion);
			
		} else {
			Promotion promotion=bundlePromotion.getPromotion();
			ProductRelationship productRelationship=bundlePromotion.getProductRelationship(); 
			bundlePromotion.setPromotion(null);
			bundlePromotion.setProductRelationship(null);
			super.save(bundlePromotion);
			
			bundlePromotion.setPromotion(promotion);
			bundlePromotion.setProductRelationship(productRelationship);
			super.update(bundlePromotion);
			
			
		}
		
	}

	

}
