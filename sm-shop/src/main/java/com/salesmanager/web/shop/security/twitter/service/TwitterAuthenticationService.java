package com.salesmanager.web.shop.security.twitter.service;

import com.salesmanager.web.shop.security.twitter.model.Twitter;

public class TwitterAuthenticationService extends OAuth1AuthenticationService<Twitter> {

	public TwitterAuthenticationService(String apiKey, String appSecret) {
		super(new TwitterConnectionFactory(apiKey, appSecret));
	}

}
