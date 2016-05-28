package com.trioplus.sm.process.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trioplus.sm.plus.process.exception.ProcessException;
import org.springframework.transaction.annotation.Transactional;
import com.trioplus.sm.process.model.CustomerShareCount;

import com.trioplus.sm.process.model.CustomerSocialShareDiscount;
import com.trioplus.sm.process.model.MerchantConfiguration;
import com.trioplus.sm.process.model.SocialMediaConfig;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@Repository("mainDao")
@Transactional
public class MainDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Get Social share config
	 */

	public SocialMediaConfig getConfig(Long merchantId, String key) throws ProcessException {

		Criteria criteria = getSession().createCriteria(MerchantConfiguration.class);
		criteria.add(Restrictions.eq("id", merchantId));
		criteria.add(Restrictions.eq("key", key));
		MerchantConfiguration merchantConfig = (MerchantConfiguration) criteria.uniqueResult();
		SocialMediaConfig config = new SocialMediaConfig();
		ObjectMapper mapper = new ObjectMapper();
		try {
			config = mapper.readValue(merchantConfig.getValue(), config.getClass());
		} catch (Exception e) {
			throw new ProcessException("Error while getting config");
		}

		return config;
	}

	/**
	 * Get customers list eligible to have social sharing discount
	 * 
	 * @param config
	 * @return
	 * @throws ProcessException
	 */

	public List<CustomerShareCount> getEligibleCustomers(SocialMediaConfig config) throws ProcessException {

		try {
			Criteria criteria = getSession().createCriteria(CustomerShareCount.class);
			criteria.add(Restrictions.ge("count", new Long(config.getShareDiscountNumber())));
			List<CustomerShareCount> elegibleCustomerList = criteria.list();
			return elegibleCustomerList;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			throw new ProcessException("Error while getting eligible customers");
		}

	}

	public void updateCustomer(CustomerShareCount customer, SocialMediaConfig config) {

		CustomerSocialShareDiscount entity = new CustomerSocialShareDiscount();

		entity.setCustomerId(customer.getId());
		entity.setCoupon("testserialnumber");
		entity.setCreationDate(new Date());
		entity.setCouponStartDate(new Date());
		entity.setDiscountPercentage(config.getDiscountPercent());
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int validDay;
        if(config.getShareDiscountIntervalUnit().longValue()==1){
        	
        	validDay=config.getShareDiscountFrequency().intValue()*7;
        }else{
        	
        	validDay=config.getShareDiscountFrequency().intValue()*30;
        	
        	
        }
        cal.add(Calendar.DATE, validDay);
		entity.setCouponEndDate(cal.getTime());
		
		getSession().persist(entity);
	
	
	}

}
