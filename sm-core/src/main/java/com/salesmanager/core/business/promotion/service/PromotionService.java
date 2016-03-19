package com.salesmanager.core.business.promotion.service;

import java.util.List;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.reference.language.model.Language;


public interface PromotionService extends SalesManagerEntityService<Long, Promotion>{
	public List<Promotion> listPromotionByCountry(Integer countryId,Language language)throws ServiceException;
	public List<Promotion> listPromotion(Language language)throws ServiceException;
	public List<Promotion> listPromotionByAge(int minAge,int maxAge)throws ServiceException;
	public List<Promotion> listPromotion(Language language, String name,
			String status, String startDate, String endDate)throws ServiceException;
	void saveOrUpdate(Promotion promotion) throws ServiceException;
}
