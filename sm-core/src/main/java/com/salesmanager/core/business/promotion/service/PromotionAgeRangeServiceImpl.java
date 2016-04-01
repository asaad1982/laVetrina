package com.salesmanager.core.business.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.promo.model.PromotionTragetAge;
import com.salesmanager.core.business.promotion.dao.PromotionTargetAgeDao;
@Service("productAgeRangeSerivce")
public class PromotionAgeRangeServiceImpl extends
		SalesManagerEntityServiceImpl<Integer, PromotionTragetAge> implements ProductAgeRangeSerivce {
	private PromotionTargetAgeDao promotionTargetAgeDao;
	@Autowired
	public PromotionAgeRangeServiceImpl(
			PromotionTargetAgeDao promotionTargetAgeDao) {
		super(promotionTargetAgeDao);
		this.promotionTargetAgeDao=promotionTargetAgeDao;
	}

	

}
