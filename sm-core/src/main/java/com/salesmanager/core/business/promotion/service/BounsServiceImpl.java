package com.salesmanager.core.business.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.promo.model.BounsType;
import com.salesmanager.core.business.promo.model.PromotionTragetAge;
import com.salesmanager.core.business.promotion.dao.BounsDao;
@Service("bounsService")
public class BounsServiceImpl extends
SalesManagerEntityServiceImpl<Long, BounsType> implements BounsService {
	BounsDao bounsDao;
	@Autowired
	public BounsServiceImpl(BounsDao genericDao) {
		super(genericDao);
		this.bounsDao=genericDao;
	}

	

}
