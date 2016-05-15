package com.salesmanager.core.business.promotion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.promo.model.PromotionType;
import com.salesmanager.core.business.promo.model.UpSellingPromotion;
@Repository("upSellingPromotionDao")
public class UpSellingPromotionDaoImpl extends SalesManagerEntityDaoImpl<Long, UpSellingPromotion>
		implements UpSellingPromotionDAO {

	

}
