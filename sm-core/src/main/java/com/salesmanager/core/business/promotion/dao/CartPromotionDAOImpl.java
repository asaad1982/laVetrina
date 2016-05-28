package com.salesmanager.core.business.promotion.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.promo.model.CartPromotion;
@Repository("cartPromotionDAO")
public class CartPromotionDAOImpl extends SalesManagerEntityDaoImpl<Long, CartPromotion> implements
CartPromotionDAO {


}
