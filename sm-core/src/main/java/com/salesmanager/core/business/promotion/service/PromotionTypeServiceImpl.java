package com.salesmanager.core.business.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.promo.model.PromotionType;
import com.salesmanager.core.business.promotion.dao.PromotionTypeDao;
@Service("promotionTypeService")
public class PromotionTypeServiceImpl extends
		SalesManagerEntityServiceImpl<Long, PromotionType> implements PromotionTypeService {
	@Autowired
	public PromotionTypeServiceImpl(
			PromotionTypeDao genericDao) {
		super(genericDao);
		this.promotionTypeDao=genericDao;
	}

	private PromotionTypeDao promotionTypeDao;
	
	

}
