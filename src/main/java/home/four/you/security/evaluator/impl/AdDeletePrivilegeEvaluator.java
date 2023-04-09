package home.four.you.security.evaluator.impl;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.AuthorityPrivilege;
import home.four.you.security.evaluator.PrivilegeEvaluator;
import home.four.you.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static home.four.you.security.auth.authorization.AuthorityPrivilege.AD_DELETE;

/**
 * Privilege evaluator for {@link AuthorityPrivilege#AD_DELETE}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdDeletePrivilegeEvaluator implements PrivilegeEvaluator {

    private final AdService adService;

    @Override
    public AuthorityPrivilege supportedPrivilege() {
        return AD_DELETE;
    }

    @Override
    public boolean evaluate(UserPrincipal caller, Serializable resourceId) throws ResourceNotFoundException {
        log.info("Evaluating AD_DELETE privilege for caller {} and resource {}", caller.getEmail(), resourceId);

        var ad = adService.findById((Long) resourceId)
                .orElseThrow(ResourceNotFoundException::new);

        return ad.getOwner().getId().equals(caller.getId())
                || caller.isAdmin();
    }
}
