package home.four.you.service.impl;

import home.four.you.model.entity.Role;
import home.four.you.model.entity.User;
import home.four.you.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CustomUserDetailsService}.
 */
@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    CustomUserDetailsService service;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        service = new CustomUserDetailsService(userService);
    }

    @Test
    @DisplayName("Load by username - ok, found")
    void loadUserByUsername_okFound() {
        String email = make();
        var user = mock(User.class);

        when(userService.findByEmail(email)).thenReturn(Optional.of(user));
        when(user.getRole()).thenReturn(new Role());

        var result = service.loadUserByUsername(email);

        assertAll(
                () -> assertThat(result.getId()).isEqualTo(user.getId()),
                () -> assertThat(result.getEmail()).isEqualTo(user.getEmail()),
                () -> assertThat(result.getFirstName()).isEqualTo(user.getFirstName()),
                () -> assertThat(result.getLastName()).isEqualTo(user.getLastName()),
                () -> assertThat(result.getAuthorities()).isEqualTo(user.getAuthorities()),
                () -> assertThat(result.getRole()).isEqualTo(user.getRole().getName())
        );
    }

    @Test
    @DisplayName("Load by username - exception thrown, user not found")
    void loadUserByUsername_exceptionThrownUserNotFound() {
        String email = make();

        when(userService.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(email));
    }
}
