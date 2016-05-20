package com.salesmanager.core.business.promotion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanager.core.business.catalog.product.service.relationship.ProductRelationshipService;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.promo.model.BundlePromotion;
import com.salesmanager.core.business.promo.model.CartPromotion;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promo.model.PromotionDescription;
import com.salesmanager.core.business.promo.model.PromotionModel;
import com.salesmanager.core.business.promo.model.PromotionRule;
import com.salesmanager.core.business.promo.model.PromotionTragetAge;
import com.salesmanager.core.business.promo.model.UpSellingPromotion;
import com.salesmanager.core.business.promotion.dao.PromotionDao;
import com.salesmanager.core.business.reference.language.model.Language;
@Service
public class PromotionServiceImpl extends SalesManagerEntityServiceImpl<Long, Promotion>
		implements PromotionService {

	private PromotionDao promotionDao;
	@Autowired
	private ProductRelationshipService productRelationshipService; 
	@Autowired
	public PromotionServiceImpl(
			PromotionDao promotionDao) {
		super(promotionDao);
		this.promotionDao=promotionDao;
	}

	@Override
	public List<Promotion> listPromotionByCountry(Integer countryId,
			Language language) throws ServiceException {
		try {
			return null;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Promotion> listPromotion(Language language)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return promotionDao.listPromotion(language);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Promotion> listPromotionByAge(int minAge, int maxAge)
			throws ServiceException {
		try {
			return promotionDao.listPromotionByAge(minAge, maxAge);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	
	public void saveOrUpdate(Promotion promotion) throws ServiceException {
		//save or update (persist and attach entities
				if(promotion.getId()!=null && promotion.getId()>0) {

					super.update(promotion);
					
				} else {
					PromotionRule promotionRule=promotion.getPromotionRule();
					List<PromotionDescription> descriptions=promotion.getPromotionDescriptions();
					promotion.setPromotionRule(null);
					promotion.setPromotionDescriptions(null);
					super.save(promotion);
					for (int i = 0; i < descriptions.size(); i++) {
						descriptions.get(i).setPromotion(promotion);
					}
					promotion.setPromotionDescriptions(descriptions);
					promotion.setPromotionRule(promotionRule);
					super.update(promotion);
					
					
				}
		
	}
	@Override
	public List<Promotion> listPromotion(Language language, String name,
			String status, String startDate, String endDate)
			throws ServiceException {
		try {
			return promotionDao.listPromotion( language,name,status,startDate,endDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public CartPromotion getCartPromotionById(long promotionId)
			throws ServiceException {


		return promotionDao.getCartPromotionById(promotionId);
	}

	@Override
	public void saveCartPromotionById(CartPromotion cartPromotion)
			throws ServiceException {
		
		
	}
	@Override
	public BundlePromotion getBundlePromotionById(long promotionId)
			throws ServiceException {


		return promotionDao.getBundlePromotionById(promotionId);
	}

	@Override
	public UpSellingPromotion getUpSellingPromotionById(long promotionId) {
		return promotionDao.getUpSellingPromotionById(promotionId);
	}

	public List<PromotionModel> listPromotionActive(MerchantStore store,Language language,Customer customer ) throws ServiceException {
		List<PromotionModel> promotionModels=new ArrayList<PromotionModel>();
		List<Promotion> promotions=null;
		if(customer==null)
			promotions=promotionDao.listPromotionActive(language);
		else
			promotions=promotionDao.listPromotionByCustomer(customer, language);
		for (Promotion promotion : promotions) {
			PromotionModel model=new PromotionModel();
			model.setPromotionId(promotion.getId());
			model.setPromotionName(promotion.getPromotionDescriptions().get(0).getName());
			if(promotion.getPromotionRule()!=null){
				model.setBrands(promotion.getPromotionRule().getBrands());
				model.setProducts(promotion.getPromotionRule().getProducts());
			}
//			if(promotion.getPromotionType().getId()==1){
//				CartPromotion cartPromotion=getCartPromotionById(promotion.getId());
//				model.setCartPromotion(cartPromotion);
//			}else 
			if(promotion.getPromotionType().getId()==4){
				BundlePromotion bundlePromotion=getBundlePromotionById(promotion.getId());
				bundlePromotion.setProductRelationships(productRelationshipService.getByGroup(store,  bundlePromotion.getProductRelationship().getCode(),
						 language));
				model.setBundlePromotion(bundlePromotion);
			}
//			else if(promotion.getPromotionType().getId()==6){
//				UpSellingPromotion sellingPromotion=getUpSellingPromotionById(promotion.getId());
//				model.setUpSellingPromotion(sellingPromotion);
//			}
			promotionModels.add(model);
		}
		return promotionModels;
	}
}
