package home.four.you.service;

import home.four.you.model.entity.Role;
import home.four.you.repository.RoleRepository;
import home.four.you.security.auth.authorization.AuthorityRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link RoleServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    RoleService service;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        service = new RoleServiceImpl(roleRepository);
    }

    @Test
    @DisplayName("Find by name - ok")
    void findByName_ok() {
        var role = Mockito.mock(Role.class);

        Mockito.when(roleRepository.findByName(AuthorityRole.ROLE_ADMIN)).thenReturn(role);

        var result = service.findByName(AuthorityRole.ROLE_ADMIN);

        assertThat(result).isEqualTo(role);
    }
}
