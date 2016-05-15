package com.salesmanager.core.business.promotion.dao;


import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.promo.model.BundlePromotion;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.reference.language.model.Language;

public interface PromotionDao extends SalesManagerEntityDao<Long, Promotion>{
public List<Promotion> listPromotionByCountry(Integer countryId,Language language);
public List<Promotion> listPromotion(Language language);
public List<Promotion> listPromotionByAge(int minAge,int maxAge);
public List<Promotion> getCustomerPromotion(Long customerId);
public List<Promotion> listPromotionByCategory(Long categoryId);
public List<Promotion> listPromotionByBrand(Long brandId);
public List<Promotion> listPromotion(Language language, String name,
		String status, String startDate, String endDate);
CartPromotion getCartPromotionById(long promotionId);
BundlePromotion getBundlePromotionById(long promotionId);

}
