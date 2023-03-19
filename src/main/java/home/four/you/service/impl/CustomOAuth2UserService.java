package home.four.you.service.impl;

import home.four.you.model.entity.User;
import home.four.you.security.UserOAuth2Principal;
import home.four.you.security.auth.GoogleUserInfo;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for user security operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        var user = userService.findByEmail(oAuth2User.getAttribute("email"))
                .orElseGet(() -> userService.createUser(new GoogleUserInfo(oAuth2User)));

        return new UserOAuth2Principal(user, oAuth2User.getAttributes());
    }

}
