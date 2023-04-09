package home.four.you.security.auth.authorization.permission;

import home.four.you.security.auth.authorization.AuthorityPrivilege;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Composed annotation for {@link AuthorityPrivilege#AD_DELETE} resource privilege.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasPermission(#id, 'ad',T(home.four.you.security.auth.authorization.AuthorityPrivilege).AD_DELETE)")
public @interface CanDeleteAd {
}
