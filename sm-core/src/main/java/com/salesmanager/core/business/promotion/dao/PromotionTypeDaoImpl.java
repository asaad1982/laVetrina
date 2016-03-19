package com.salesmanager.core.business.promotion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.promo.model.PromotionType;
@Repository("promotionTypeDao")
public class PromotionTypeDaoImpl extends SalesManagerEntityDaoImpl<Long, PromotionType>
		implements PromotionTypeDao {

	

}
