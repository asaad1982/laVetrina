package com.salesmanager.web.shop.security.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.salesmanager.web.shop.security.model.LocalUser;
import com.salesmanager.web.shop.security.model.SocialProvider;
import com.salesmanager.web.shop.security.model.UserRegistrationForm;
import com.salesmanager.web.shop.security.service.UserService;
import com.salesmanager.web.shop.security.twitter.model.TwitterTemplate;
import com.salesmanager.web.shop.security.util.SecurityUtil;



/**
 * If no local user associated with the given connection then
 * connection signup will create a new local user from the given connection.
 *
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 27/3/16
 */
public class AppConnectionSignUp implements ConnectionSignUp {

//    @Autowired
//    private UserService userService;
    
    @Autowired
    @Qualifier(value = "customerDetailsService")
    private UserDetailsService userDetailService;

    @Override
    public String execute(final Connection<?> connection) {
    	
    	String email = null;
    	String provider = connection.getKey().getProviderId();
    	if(provider.equals("twitter"))
    		email = ((TwitterTemplate) connection.getApi()).userOperations().getUserProfile().getEmail();
    	else if(provider.equals("facebook"))
    		email = connection.fetchUserProfile().getEmail();
    		
    	
    	
        UserRegistrationForm userDetails = toUserRegistrationObject(connection.getKey().getProviderUserId(), SecurityUtil.toSocialProvider(connection.getKey().getProviderId()), connection.fetchUserProfile(), email);
        LocalUser user = (LocalUser) userDetailService.loadUserByUsername(userDetails.getEmail());
//        if(user == null)
//        	user = (LocalUser) userService.registerNewUser(userDetails);
        return user.getUserId();
    }

    private UserRegistrationForm toUserRegistrationObject(final String userId, final SocialProvider socialProvider, final UserProfile userProfile, final String email) {
        return UserRegistrationForm.getBuilder()
                .addUserId(userId)
                .addFirstName(userProfile.getName())
                .addEmail(email)
                .addPassword(userProfile.getName())
                .addSocialProvider(socialProvider).build();
    }

}
