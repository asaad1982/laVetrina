package com.salesmanager.web.shop.security.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterAdapter;

import com.salesmanager.web.shop.security.model.LocalUser;
import com.salesmanager.web.shop.security.model.SocialProvider;
import com.salesmanager.web.shop.security.model.UserRegistrationForm;
import com.salesmanager.web.shop.security.service.UserService;
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
    	
        UserRegistrationForm userDetails = toUserRegistrationObject(connection.getKey().getProviderUserId(), SecurityUtil.toSocialProvider(connection.getKey().getProviderId()), connection.fetchUserProfile());
        LocalUser user = (LocalUser) userDetailService.loadUserByUsername(userDetails.getEmail());
//        if(user == null)
//        	user = (LocalUser) userService.registerNewUser(userDetails);
        return user.getUserId();
    }

    private UserRegistrationForm toUserRegistrationObject(final String userId, final SocialProvider socialProvider, final UserProfile userProfile) {
        return UserRegistrationForm.getBuilder()
                .addUserId(userId)
                .addFirstName(userProfile.getName())
                .addEmail(userProfile.getEmail())
                .addPassword(userProfile.getName())
                .addSocialProvider(socialProvider).build();
    }

}
