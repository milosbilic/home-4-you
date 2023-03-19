package home.four.you.service.impl;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.entity.Role;
import home.four.you.model.entity.User;
import home.four.you.repository.UserRepository;
import home.four.you.security.auth.GoogleUserInfo;
import home.four.you.security.auth.authorization.AuthorityRole;
import home.four.you.service.RoleService;
import home.four.you.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static home.four.you.TestUtil.generateId;
import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link UserServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    UserService service;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleService roleService;

    @Mock
    User user;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(userRepository, roleService);
    }

    @Test
    @DisplayName("Find by ID - ok, found")
    void findById_okFound() {
        Long id = generateId();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        var result = service.findById(id);

        assertThat(result).isEqualTo(user);
    }

    @Test
    @DisplayName("Find by ID - not found")
    void findById_notFound() {
        Long id = generateId();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(id));
    }

    @Test
    @DisplayName("Find by email - ok")
    void findByEmail_ok() {
        String email = make();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        var result = service.findByEmail(email);

        assertThat(result).hasValue(user);
    }

    @Test
    @DisplayName("Create user from OAuth2 - ok")
    void createUserFromOAuth2_ok() {
        var googleInfo = mock(GoogleUserInfo.class);

        when(roleService.findByName(AuthorityRole.ROLE_USER)).thenReturn(new Role());

        service.createUser(googleInfo);

        verify(userRepository, times(1)).save(any());
    }
}
