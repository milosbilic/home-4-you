package home.four.you.security.evaluator;

import home.four.you.security.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static home.four.you.TestUtil.generateId;
import static home.four.you.security.auth.authorization.AuthorityPrivilege.AD_DELETE;
import static java.util.Set.of;
import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ResourcePermissionEvaluator}.
 */
@ExtendWith(MockitoExtension.class)
class ResourcePermissionEvaluatorTest {

    private ResourcePermissionEvaluator evaluator;
    @Mock
    private PrivilegeEvaluationHandler handler;
    @Mock
    private Authentication authentication;
    @Mock
    private UserPrincipal userPrincipal;

    @BeforeEach
    void setup() {
        evaluator = new ResourcePermissionEvaluator(handler);
    }

    @Test
    @DisplayName("Permission for object not implemented")
    void permissionForObject() {
        assertThat(evaluator.hasPermission(authentication, null, null)).isFalse();
    }

    @Test
    @DisplayName("Not authenticated")
    void notAuthenticated() {
        when(authentication.getPrincipal()).thenReturn(null);
        assertThat(evaluator.hasPermission(authentication, generateId().toString(), make(),
                AD_DELETE)).isFalse();
        verifyNoMoreInteractions(handler);
    }

    @Test
    @DisplayName("Not authenticated")
    void noPrivilege() {
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        assertThat(evaluator.hasPermission(authentication, generateId().toString(), make(),
                AD_DELETE)).isFalse();
        verifyNoMoreInteractions(handler);
    }

    @Test
    @DisplayName("Not permitted")
    void notPermitted() {
        var resourceId = generateId().toString();
        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        assertThat(evaluator.hasPermission(authentication, resourceId, make(), AD_DELETE)).isFalse();
    }

    @Test
    @DisplayName("Permitted")
    void permitted() {
        var email = make();
        var userPrincipal = new UserPrincipal()
                .setEmail(email)
                .setAuthorities(of(new SimpleGrantedAuthority(AD_DELETE.name())));
        var resourceId = generateId().toString();
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        when(handler.handle(any(), eq(resourceId), eq(AD_DELETE))).thenReturn(true);

        assertThat(evaluator.hasPermission(authentication, resourceId, make(), AD_DELETE)).isTrue();
    }

    @Test
    @DisplayName("Not permitted - can't handle")
    void notPermitted_cantHandle() {
        var email = make();
        var userPrincipal = new UserPrincipal()
                .setEmail(email)
                .setAuthorities(of(new SimpleGrantedAuthority(AD_DELETE.name())));
        var resourceId = generateId().toString();
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        when(handler.handle(any(), eq(resourceId), eq(AD_DELETE))).thenReturn(false);

        assertThat(evaluator.hasPermission(authentication, resourceId, make(), AD_DELETE)).isFalse();
    }
}
