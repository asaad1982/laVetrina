package com.salesmanager.core.business.promotion.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.promo.model.BundlePromotion;

@Repository("bundlePromotionDAO")
public class BundlePromotionDAOImpl extends SalesManagerEntityDaoImpl<Long, BundlePromotion> implements
BundlePromotionDAO {


}
