package home.four.you.security.evaluator.impl;

import home.four.you.model.entity.User;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.AuthorityPrivilege;
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
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link UserDeletePrivilegeEvaluator}.
 */
@ExtendWith(MockitoExtension.class)
class UserDeletePrivilegeEvaluatorTest {

    UserDeletePrivilegeEvaluator evaluator;

    @Mock
    UserService userService;

    @Mock
    UserPrincipal caller;

    @Mock
    User user;

    @BeforeEach
    void setUp() {
        evaluator = new UserDeletePrivilegeEvaluator(userService);
    }

    @Test
    @DisplayName("Supported privilege")
    void supportedPrivilege() {
        assertThat(evaluator.supportedPrivilege()).isEqualTo(AuthorityPrivilege.USER_DELETE);
    }

    @Test
    @DisplayName("Allowed for related user")
    void allowedForRelatedUser() {
        Long id = generateId();

        when(userService.findById(id)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(id);
        when(caller.getId()).thenReturn(id);

        assertThat(evaluator.evaluate(caller, id)).isTrue();
    }

    @Test
    @DisplayName("Allowed for admin")
    void allowedForAdmin() {
        Long id = generateId();

        when(userService.findById(id)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(generateId());
        when(caller.isAdmin()).thenReturn(true);

        assertThat(evaluator.evaluate(caller, id)).isTrue();
    }

    @Test
    @DisplayName("Forbidden")
    void forbidden() {
        Long id = generateId();

        when(userService.findById(id)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(generateId());
        when(caller.isAdmin()).thenReturn(false);

        assertThat(evaluator.evaluate(caller, id)).isFalse();
    }
}
