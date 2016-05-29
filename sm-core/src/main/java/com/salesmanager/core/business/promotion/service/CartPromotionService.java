package com.salesmanager.core.business.promotion.service;

import java.util.List;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promo.model.PromotionDescription;
import com.salesmanager.core.business.promo.model.PromotionRule;

public interface CartPromotionService extends SalesManagerEntityService<Long, CartPromotion>{
	public void saveOrUpdate(CartPromotion cartPromotion) throws ServiceException;

}
