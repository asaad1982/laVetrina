package com.salesmanager.core.business.promotion.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;














import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.promo.model.Promotion;
import com.salesmanager.core.business.promo.model.QPromotion;
import com.salesmanager.core.business.promo.model.QPromotionDescription;
import com.salesmanager.core.business.promo.model.QPromotionRule;
import com.salesmanager.core.business.reference.language.model.Language;
@Repository("promotionDao")
public class PromotionDaoImpl extends SalesManagerEntityDaoImpl<Long, Promotion> implements PromotionDao {

	@Override
	public List<Promotion> listPromotionByCountry(Integer countryId,Language language) {
		QPromotion qPromotion=QPromotion.promotion;
		QPromotionDescription qDescription=QPromotionDescription.promotionDescription;
		QPromotionRule qPromotionRule=QPromotionRule.promotionRule;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qPromotion)
		.leftJoin(qPromotion.promotionDescriptions, qDescription).fetch()
		.rightJoin(qPromotion.promotionRule,qPromotionRule).fetch()
		.where(qDescription.languageId.eq(language.getId())
				.and(qPromotionRule.countries.any().id.eq(countryId))
				);
		
		
		return query.list(qPromotion);
	}

	@Override
	public List<Promotion> listPromotion(Language language) {
		QPromotion qPromotion=QPromotion.promotion;
		QPromotionDescription qDescription=QPromotionDescription.promotionDescription;
		QPromotionRule qPromotionRule=QPromotionRule.promotionRule;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qPromotion)
		.leftJoin(qPromotion.promotionDescriptions, qDescription).fetch()
		.leftJoin(qPromotion.promotionRule,qPromotionRule)
		.where(qDescription.languageId.eq(language.getId())
				
				);
		
		
		return query.distinct().list(qPromotion);
	}

	@Override
	public List<Promotion> listPromotionByAge(int minAge, int maxAge) {
		QPromotion qPromotion=QPromotion.promotion;
		QPromotionDescription qDescription=QPromotionDescription.promotionDescription;
		QPromotionRule qPromotionRule=QPromotionRule.promotionRule;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qPromotion)
		.leftJoin(qPromotion.promotionDescriptions, qDescription).fetch()
		.leftJoin(qPromotion.promotionRule,qPromotionRule)
		.where((qPromotionRule.promotionTragetAge.minVal.eq(minAge)).and(qPromotionRule.promotionTragetAge.maxVal.eq(maxAge)))
				;
		
		
		return query.distinct().list(qPromotion);
	}

	@Override
	public List<Promotion> getCustomerPromotion(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> listPromotionByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> listPromotionByBrand(Long brandId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> listPromotion(Language language, String name,
			String status, String startDate, String endDate) {
		QPromotion qPromotion=QPromotion.promotion;
		QPromotionDescription qDescription=QPromotionDescription.promotionDescription;
		QPromotionRule qPromotionRule=QPromotionRule.promotionRule;
		BooleanExpression predicate=qDescription.languageId.eq(language.getId());
		if(!StringUtils.isBlank(name)){
			predicate.and(qDescription.name.like("%"+name+"%"));
		}if(!StringUtils.isBlank(status)){
			if(predicate!=null)
			predicate.and(qPromotion.status.eq(status));
			
		}if(!StringUtils.isBlank(startDate)){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date compDate=new Date();
			try {
				compDate = formatter.parse(startDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(predicate!=null)
			predicate.and(qPromotion.startDate.goe(compDate));
			
		}if(!StringUtils.isBlank(endDate)){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date compDate=new Date();
			try {
				compDate = formatter.parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(predicate!=null)
			predicate.and(qPromotion.endate.loe(compDate));
			
		}
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qPromotion)
		.leftJoin(qPromotion.promotionDescriptions, qDescription).fetch()
		.leftJoin(qPromotion.promotionRule,qPromotionRule)
		.where(predicate
			
				);
		
		
		return query.distinct().list(qPromotion);
	}



	

}
