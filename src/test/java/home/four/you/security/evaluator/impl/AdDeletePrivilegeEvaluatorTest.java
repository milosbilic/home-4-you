package home.four.you.security.evaluator.impl;

import home.four.you.model.entity.Ad;
import home.four.you.model.entity.User;
import home.four.you.security.UserPrincipal;
import home.four.you.service.AdService;
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
import static home.four.you.security.auth.authorization.AuthorityPrivilege.AD_DELETE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AdDeletePrivilegeEvaluator}.
 */
@ExtendWith(MockitoExtension.class)
class AdDeletePrivilegeEvaluatorTest {

    AdDeletePrivilegeEvaluator evaluator;

    @Mock
    AdService adService;

    @Mock
    UserPrincipal caller;

    @Mock
    User user;

    @Mock
    Ad ad;

    @BeforeEach
    void setUp() {
        evaluator = new AdDeletePrivilegeEvaluator(adService);
    }

    @Test
    @DisplayName("supported privilege")
    void supportedPrivilege() {
        assertThat(evaluator.supportedPrivilege()).isEqualTo(AD_DELETE);
    }

    @Test
    @DisplayName("When owner calling - then allowed")
    void whenOwnerCalling_thenAllowed() {
        Long id = generateId();
        Long userId = generateId();

        when(adService.findById(id)).thenReturn(Optional.of(ad));
        when(ad.getOwner()).thenReturn(user);
        when(user.getId()).thenReturn(userId);
        when(caller.getId()).thenReturn(userId);

        var result = evaluator.evaluate(caller, id);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("When admin calling - then allowed")
    void whenAdminCalling_thenAllowed() {
        Long id = generateId();

        when(adService.findById(id)).thenReturn(Optional.of(ad));
        when(ad.getOwner()).thenReturn(user);
        when(user.getId()).thenReturn(generateId());
        when(caller.isAdmin()).thenReturn(true);

        var result = evaluator.evaluate(caller, id);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("When unrelated user calling - then forbidden")
    void whenUnrelatedUserCalling_thenForbidden() {
        Long id = generateId();

        when(adService.findById(id)).thenReturn(Optional.of(ad));
        when(ad.getOwner()).thenReturn(user);
        when(user.getId()).thenReturn(generateId());

        var result = evaluator.evaluate(caller, id);

        assertThat(result).isFalse();
    }
}
