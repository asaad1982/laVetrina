package com.salesmanager.core.business.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promo.model.PromotionDescription;
import com.salesmanager.core.business.promo.model.PromotionRule;
import com.salesmanager.core.business.promotion.dao.CartPromotionDAO;
@Service("cartPromotionService")
public class CartPromotionServiceImpl extends SalesManagerEntityServiceImpl<Long, CartPromotion>
implements CartPromotionService{
	@Autowired
private CartPromotionDAO cartPromotionDAO;
	
	@Autowired
	public CartPromotionServiceImpl(
			CartPromotionDAO cartPromotionDAO) {
		super(cartPromotionDAO);
		this.cartPromotionDAO=cartPromotionDAO;
	}
	
	public void saveOrUpdate(CartPromotion cartPromotion) throws ServiceException {
		//save or update (persist and attach entities
				if(cartPromotion.getId()!=null && cartPromotion.getId()>0) {

					super.update(cartPromotion);
					
				} else {
					Promotion promotion=cartPromotion.getPromotion();
					cartPromotion.setPromotion(null);
					
					super.save(cartPromotion);
					
					cartPromotion.setPromotion(promotion);
					super.update(cartPromotion);
					
					
				}
		
	}
}
