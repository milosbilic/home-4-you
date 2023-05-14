package home.four.you.controller;

import home.four.you.model.dto.LoginRequestDto;
import home.four.you.security.auth.EmailAndPasswordAuthManager;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.bytebuddy.utility.RandomString.make;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AuthController}.
 */
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    EmailAndPasswordAuthManager emailAndPasswordAuthManager;

    AuthController controller;

    @BeforeEach
    void setUp() {
        controller = new AuthController(emailAndPasswordAuthManager);
    }

    @Test
    @DisplayName("Login")
    void login() {
        var dto = mock(LoginRequestDto.class);
        var response = mock(HttpServletResponse.class);

        when(emailAndPasswordAuthManager.authenticate(dto)).thenReturn(make());

        controller.login(dto, response);
    }
}
