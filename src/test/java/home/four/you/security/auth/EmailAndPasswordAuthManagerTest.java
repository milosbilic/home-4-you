package home.four.you.security.auth;

import home.four.you.model.dto.LoginRequestDto;
import home.four.you.model.entity.User;
import home.four.you.security.TokenProvider;
import home.four.you.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link EmailAndPasswordAuthManager}.
 */
@ExtendWith(MockitoExtension.class)
class EmailAndPasswordAuthManagerTest {

    @Mock
    UserService userService;

    @Mock
    TokenProvider tokenProvider;

    @Mock
    PasswordEncoder passwordEncoder;

    EmailAndPasswordAuthManager authManager;

    @BeforeEach
    void setUp() {
        authManager = new EmailAndPasswordAuthManager(userService, tokenProvider, passwordEncoder);
    }

    @Test
    @DisplayName("Authenticate - user not found")
    void authenticate_userNotFound() {
        var login = mock(LoginRequestDto.class);

        when(userService.findByEmail(login.email())).thenReturn(Optional.empty());

        assertThrows(AccessDeniedException.class, () -> authManager.authenticate(login));
    }

    @Test
    @DisplayName("Authenticate - password does not match")
    void authenticate_passwordDoesNotMatch() {
        var login = mock(LoginRequestDto.class);
        var user = mock(User.class);

        when(userService.findByEmail(login.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(login.password(), user.getPassword())).thenReturn(false);

        assertThrows(AccessDeniedException.class, ()-> authManager.authenticate(login));
    }

    @Test
    @DisplayName("Authenticate - ok")
    void authenticate_ok() {
        var login = mock(LoginRequestDto.class);
        var user = mock(User.class);
        var email = make();
        String token = make();

        when(login.email()).thenReturn(email);
        when(userService.findByEmail(login.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(login.password(), user.getPassword())).thenReturn(true);
        when(tokenProvider.createToken(user)).thenReturn(token);

        var result = authManager.authenticate(login);

        assertThat(result).isEqualTo(token);
    }
}
