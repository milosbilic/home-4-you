package home.four.you.security.evaluator;

import home.four.you.security.ResourceCaller;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.AuthorityPrivilege;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

import static java.util.Optional.ofNullable;

/**
 * Resource permission evaluator.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourcePermissionEvaluator implements PermissionEvaluator {

    private final PrivilegeEvaluationHandler handler;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject,
                                 Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        log.debug("Checking caller [{}] permission for target [{}] and privilege [{}]",
                authentication.getName(), targetType, permission);

        var privilege = AuthorityPrivilege.valueOf(permission.toString());
        boolean hasOwnership = ofNullable(authentication.getPrincipal())
                .map(principal -> (UserPrincipal) principal)
                .filter(principal -> hasPrivilege(principal.getAuthorities(), privilege))
                .map(this::mapToResourceCaller)
                .map(resourceCaller -> handler.handle(resourceCaller, targetId, privilege))
                .orElse(false);

        log.debug("Ownership resolved to : {}", hasOwnership);
        return hasOwnership;
    }

    private boolean hasPrivilege(Collection<SimpleGrantedAuthority> authorities,
                                 AuthorityPrivilege privilege) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .anyMatch(s -> s.equals(privilege.name()));
    }

    private ResourceCaller mapToResourceCaller(UserPrincipal principal) {
        return ResourceCaller.builder()
                .admin(principal.isAdmin())
                .id(principal.getId())
                .callerId(principal.getCallerId())
                .build();
    }

}
