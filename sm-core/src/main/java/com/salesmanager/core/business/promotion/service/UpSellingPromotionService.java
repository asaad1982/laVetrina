package com.salesmanager.core.business.promotion.service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.promo.model.BundlePromotion;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.UpSellingPromotion;

public interface UpSellingPromotionService extends SalesManagerEntityService<Long, UpSellingPromotion>{
	public void saveOrUpdate(UpSellingPromotion upSellingPromotion) throws ServiceException;



}
