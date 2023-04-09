package home.four.you.security.evaluator;

import home.four.you.security.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static home.four.you.TestUtil.generateId;
import static home.four.you.security.auth.authorization.AuthorityPrivilege.AD_DELETE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link PrivilegeEvaluationHandlerImpl}.
 */
@ExtendWith(MockitoExtension.class)
class PrivilegeEvaluationHandlerImplTest {

    private PrivilegeEvaluationHandlerImpl handler;
    @Mock
    private ApplicationContext ctxMock;
    @Mock
    private UserPrincipal user;
    @Mock
    private PrivilegeEvaluator evaluator;

    @BeforeEach
    void setup() {
        handler = new PrivilegeEvaluationHandlerImpl();
    }

    @Test
    @DisplayName("Initialization - with context")
    void init() {
        handler.setApplicationContext(ctxMock);

        verify(ctxMock).getBeansOfType(PrivilegeEvaluator.class);
    }

    @Test
    @DisplayName("Handle evaluation - no matching evaluator")
    void handleNoEvaluator() {
        handler.setApplicationContext(ctxMock);

        boolean result = handler.handle(user, generateId(), AD_DELETE);

        verify(ctxMock).getBeansOfType(PrivilegeEvaluator.class);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Handle evaluation - not owner")
    void handleNotOwner() {
        Long resourceId = generateId();
        when(ctxMock.getBeansOfType(PrivilegeEvaluator.class)).thenReturn(
                Map.of("evaluator", evaluator));
        when(evaluator.supportedPrivilege()).thenReturn(AD_DELETE);
        when(evaluator.evaluate(user, resourceId)).thenReturn(false);
        handler.setApplicationContext(ctxMock);

        boolean result = handler.handle(user, resourceId, AD_DELETE);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Handle evaluation - owner")
    void handleOwner() {
        Long resourceId = generateId();
        when(ctxMock.getBeansOfType(PrivilegeEvaluator.class)).thenReturn(
                Map.of("evaluator", evaluator));
        when(evaluator.supportedPrivilege()).thenReturn(AD_DELETE);
        when(evaluator.evaluate(user, resourceId)).thenReturn(true);
        handler.setApplicationContext(ctxMock);

        boolean result = handler.handle(user, resourceId, AD_DELETE);

        assertThat(result).isTrue();
    }
}