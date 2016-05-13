package com.salesmanager.web.shop.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.salesmanager.web.shop.security.exception.UserAlreadyExistAuthenticationException;
import com.salesmanager.web.shop.security.model.UserRegistrationForm;



/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 28/3/16
 */
public interface UserService {

    public UserDetails registerNewUser(UserRegistrationForm UserRegistrationForm)throws UserAlreadyExistAuthenticationException;

}
