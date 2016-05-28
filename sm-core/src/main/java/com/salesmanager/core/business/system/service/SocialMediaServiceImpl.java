/**
 * 
 */
package com.salesmanager.core.business.system.service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.modules.email.EmailConfig;
import com.salesmanager.core.modules.social.SocialMediaConfig;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.model.MerchantConfiguration;
import com.salesmanager.core.constants.Constants;



/**
 * @author Administrator
 *
 */

@Service("socialMediaService")
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private MerchantConfigurationService merchantConfigurationService;
	/* (non-Javadoc)
	 * @see com.salesmanager.core.business.system.service.SocialMediaService#getEmailConfiguration(com.salesmanager.core.business.merchant.model.MerchantStore)
	 */
	@Override
	public SocialMediaConfig getConfiguration(MerchantStore store) throws ServiceException {
		
		SocialMediaConfig config= new SocialMediaConfig();
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(Constants.SOCIAL_MEDIA_CONFIG, store);
		if(configuration!=null) {
			String value = configuration.getValue();
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				config = mapper.readValue(value, SocialMediaConfig.class);
			} catch(Exception e) {
				throw new ServiceException("Cannot parse json string " + value);
			}
		}
		return config;
	
	}

	/* (non-Javadoc)
	 * @see com.salesmanager.core.business.system.service.SocialMediaService#saveEmailConfiguration(com.salesmanager.core.modules.social.SocialMediaConfig, com.salesmanager.core.business.merchant.model.MerchantStore)
	 */
	@Override
	public void saveConfiguration(SocialMediaConfig config, MerchantStore store) throws ServiceException {
		
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(Constants.SOCIAL_MEDIA_CONFIG, store);
		
		
		if(configuration==null){
			configuration = new MerchantConfiguration();
			configuration.setMerchantStore(store);
			configuration.setKey(Constants.SOCIAL_MEDIA_CONFIG);
		}
		
		String value = config.toJSONString();
		configuration.setValue(value);
		merchantConfigurationService.saveOrUpdate(configuration);

	}

}
