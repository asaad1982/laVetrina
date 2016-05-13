package com.salesmanager.web.shop.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 28/3/16
 */
public class UserNotRegisteredException extends AuthenticationException {

    public UserNotRegisteredException(final String msg) {
        super(msg);
    }

}
