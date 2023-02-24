package home.four.you.service.impl;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.entity.User;
import home.four.you.repository.UserRepository;
import home.four.you.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static home.four.you.TestUtil.generateId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link UserServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    UserService service;

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(userRepository);
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
}
