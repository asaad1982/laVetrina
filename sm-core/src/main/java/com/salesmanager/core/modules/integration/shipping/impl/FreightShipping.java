/**
 * 
 */
package com.salesmanager.core.modules.integration.shipping.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import com.salesmanager.core.modules.integration.IntegrationException;

import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.business.system.model.ModuleConfig;
import com.salesmanager.core.business.system.service.ModuleConfigurationService;
import com.salesmanager.core.modules.integration.shipping.model.OrderModel;
import com.salesmanager.core.modules.integration.shipping.model.RateRequestModel;
import com.salesmanager.core.modules.integration.shipping.model.RateResponseModel;


/**
 * @author Administrator
 *
 */

/**
 * Integrates with freight online API
 * @author casams1
 *
 */


@Component
public class FreightShipping {



	@Autowired
	private ModuleConfigurationService moduleConfigurationService;
	
	private final static String FREIGHT_SHIPPING_MODULES = "FREIGHT_SHIPPING";
	
/**
 * Return service configuration 	
 * @return
 * @throws ServiceException
 */
	private ModuleConfig getShippingConfig(String key)  {
		List<IntegrationModule> modules =  moduleConfigurationService.getIntegrationModules(FREIGHT_SHIPPING_MODULES);
		Map<String, ModuleConfig> moduleConfigsMap = modules.get(0).getModuleConfigs();
		ModuleConfig moduleConfig=moduleConfigsMap.get(key);
		return moduleConfig;
		
		
	}
	
	
/**
 * 	
 * @param packingslipno
 * @return
 * @throws IntegrationException
 */
	
	public OrderModel getOrder(String packingslipno) throws  IntegrationException{
		try {
			ModuleConfig moduleConfig=getShippingConfig("getOrder");
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			requestHeaders.set("access_key", moduleConfig.getConfig1());
			String url = moduleConfig.getScheme()+"://"+moduleConfig.getHost()+":"+moduleConfig.getPort()+moduleConfig.getUri()+"?packingslipno"+packingslipno;
			RestTemplate client= new RestTemplate();
			OrderModel response=null;
			response=client.exchange(url,HttpMethod.GET,new HttpEntity<Object>(requestHeaders) ,response.getClass()).getBody();
			return response;
		}catch(HttpStatusCodeException  ex){
			throw new IntegrationException(ex.getStatusCode().toString()+ex.getStatusText());
		} catch (RestClientException ex) {
			throw new IntegrationException(ex.getMessage());
		}catch (Exception ex) {
			throw new IntegrationException(ex.getMessage());
		}

		
	}
 	
	/**
	 * 
	 * @param order
	 * @throws ServiceException
	 */
	
	public void createOrder(OrderModel requestModel) throws  IntegrationException {
		try {
		ModuleConfig moduleConfig=getShippingConfig("newOrder");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set("access_key", moduleConfig.getConfig1());
		String url = moduleConfig.getScheme()+"://"+moduleConfig.getHost()+":"+moduleConfig.getPort()+moduleConfig.getUri();
		RestTemplate client= new RestTemplate();
		String response=client.exchange(url,HttpMethod.GET,new HttpEntity(requestModel,requestHeaders) ,String.class).getBody();
		}catch(HttpStatusCodeException  ex){
			throw new IntegrationException(ex.getStatusCode().toString()+ex.getStatusText());
		} catch (RestClientException ex) {
			throw new IntegrationException(ex.getMessage());
		}catch (Exception ex) {
			throw new IntegrationException(ex.getMessage());
		}
	
	}

	
/**
 * 
 * @param requestModel
 * @throws IntegrationException
 */
	
	public void getAvailableRates(RateRequestModel requestModel) throws  IntegrationException {
		try {
		ModuleConfig moduleConfig=getShippingConfig("newOrder");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set("access_key", moduleConfig.getConfig1());
		String url = moduleConfig.getScheme()+"://"+moduleConfig.getHost()+":"+moduleConfig.getPort()+moduleConfig.getUri();
		RestTemplate client= new RestTemplate();
		RateResponseModel  response=client.exchange(url,HttpMethod.POST,new HttpEntity(requestModel,requestHeaders) ,RateResponseModel.class).getBody();
		}catch(HttpStatusCodeException  ex){
			throw new IntegrationException(ex.getStatusCode().toString()+ex.getStatusText());
		} catch (RestClientException ex) {
			throw new IntegrationException(ex.getMessage());
		}catch (Exception ex) {
			throw new IntegrationException(ex.getMessage());
		}
	
	}

}
