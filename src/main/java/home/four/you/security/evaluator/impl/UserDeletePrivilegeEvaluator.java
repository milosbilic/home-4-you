package home.four.you.security.evaluator.impl;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.AuthorityPrivilege;
import home.four.you.security.evaluator.PrivilegeEvaluator;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Privilege evaluator for {@link AuthorityPrivilege#USER_DELETE}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDeletePrivilegeEvaluator implements PrivilegeEvaluator {

    private final UserService userService;

    @Override
    public AuthorityPrivilege supportedPrivilege() {
        return AuthorityPrivilege.USER_DELETE;
    }

    @Override
    public boolean evaluate(UserPrincipal caller, Serializable resourceId) throws ResourceNotFoundException {
        log.debug("Evaluating AD_DELETE privilege for caller {} and resource {}", caller.getEmail(), resourceId);

        var user = userService.findById((Long) resourceId)
                .orElseThrow(ResourceNotFoundException::new);

        return user.getId().equals(caller.getId()) || caller.isAdmin();
    }
}
