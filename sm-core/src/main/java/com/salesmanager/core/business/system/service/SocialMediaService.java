/**
 * 
 */
package com.salesmanager.core.business.system.service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.modules.email.EmailConfig;
import com.salesmanager.core.modules.social.SocialMediaConfig;

/**
 * @author Administrator
 *
 */
public interface SocialMediaService {


	public SocialMediaConfig getConfiguration(MerchantStore store) throws ServiceException;
	
	public void saveConfiguration(SocialMediaConfig config, MerchantStore store) throws ServiceException;
	

}
