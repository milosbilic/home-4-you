package home.four.you.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link OAuth2AuthenticationFailureHandler}.
 */
@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationFailureHandlerTest {

    OAuth2AuthenticationFailureHandler handler;

    @BeforeEach
    void setUp() {
        handler = new OAuth2AuthenticationFailureHandler();
    }

    @Test
    @DisplayName("Auth failed - response sent")
    void authFailedResponseSent() throws IOException {
        var request = mock(HttpServletRequest.class);
        var response = mock(HttpServletResponse.class);
        var ex = mock(AuthenticationException.class);

        handler.onAuthenticationFailure(request, response, ex);

        verify(response, times(1)).sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }
}
