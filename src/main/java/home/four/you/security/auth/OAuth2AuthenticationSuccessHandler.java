package home.four.you.security.auth;

import home.four.you.security.TokenProvider;
import home.four.you.security.UserOAuth2Principal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Authentication success handler.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {
        log.info("Handling success authentication");

        var principal = (UserOAuth2Principal) authentication.getPrincipal();

        String token = tokenProvider.createToken(principal.getUser());

        response.addHeader("Authorization", "Bearer " + token);
    }

}
