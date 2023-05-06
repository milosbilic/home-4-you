package home.four.you.service.impl;

import home.four.you.exception.BadRequestException;
import home.four.you.model.dto.CreateUserRequestDto;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static home.four.you.TestUtil.generateId;
import static home.four.you.exception.ErrorCode.BAD_REQUEST;
import static home.four.you.exception.ErrorMessage.USER_EXISTS;
import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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
    PasswordEncoder passwordEncoder;

    @Mock
    User user;

    @Mock
    CreateUserRequestDto dto;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(userRepository, roleService, passwordEncoder);
    }

    @Test
    @DisplayName("Get by ID - ok")
    void getById_ok() {
        Long id = generateId();

        when(userRepository.getReferenceById(id)).thenReturn(user);

        var result = service.getById(id);

        assertThat(result).isEqualTo(user);
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

    @Test
    @DisplayName("Create user - ok")
    void createUser_ok() {
        when(userRepository.existsByEmail(dto.email())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        var result = service.createUser(dto);

        assertThat(result).isEqualTo(user);
    }

    @Test
    @DisplayName("Create user - user already exists")
    void createUser_userAlreadyExists() {
        when(userRepository.existsByEmail(dto.email())).thenReturn(true);

        var ex = assertThrows(BadRequestException.class, () -> service.createUser(dto));

        assertAll(
                () -> assertThat(ex.getCode()).isEqualTo(BAD_REQUEST),
                () -> assertThat(ex.getMessage()).isEqualTo(USER_EXISTS)
        );
    }

    @Test
    @DisplayName("Find by ID - ok")
    void findById_ok() {
        Long id = generateId();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        var result = service.findById(id);

        assertThat(result).hasValue(user);
    }

    @Test
    @DisplayName("Delete - ok")
    void delete_ok() {
        Long id = generateId();

        service.delete(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Find all - ok")
    void findAll_ok() {
        var pageable = mock(Pageable.class);
        PageImpl<User> users = new PageImpl<>(List.of(user));

        when(userRepository.findAll(pageable)).thenReturn(users);

        var result = service.findAll(pageable);

        assertThat(result).isEqualTo(users);
    }
}
