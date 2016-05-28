package com.salesmanager.core.business.promotion.service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.promo.model.BundlePromotion;
import com.salesmanager.core.business.promo.model.CartPromotion;

public interface BundlePromotionService extends SalesManagerEntityService<Long, BundlePromotion>{
	public void saveOrUpdate(BundlePromotion bundlePromotion) throws ServiceException;



}
