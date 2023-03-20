package home.four.you.security.auth;

import home.four.you.model.entity.User;
import home.four.you.security.TokenProvider;
import home.four.you.security.UserOAuth2Principal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static net.bytebuddy.utility.RandomString.make;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link OAuth2AuthenticationSuccessHandler}.
 */
@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationSuccessHandlerTest {

    OAuth2AuthenticationSuccessHandler handler;

    @Mock
    TokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        handler = new OAuth2AuthenticationSuccessHandler(tokenProvider);
    }

    @Test
    @DisplayName("On authentication success")
    void onAuthenticationSuccess() {
        var request = mock(HttpServletRequest.class);
        var response = mock(HttpServletResponse.class);
        var auth = mock(Authentication.class);
        var principal = mock(UserOAuth2Principal.class);
        User user = mock(User.class);
        String token = make();

        when(auth.getPrincipal()).thenReturn(principal);
        when(principal.getUser()).thenReturn(user);
        when(tokenProvider.createToken(user)).thenReturn(token);

        handler.onAuthenticationSuccess(request, response, auth);

        verify(response, times(1)).addHeader("Authorization", "Bearer " + token);
    }
}
