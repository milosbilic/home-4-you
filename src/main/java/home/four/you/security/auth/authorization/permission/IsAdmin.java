package home.four.you.security.auth.authorization.permission;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole("
        + "T(home.four.you.security.auth.authorization.AuthorityRole)"
        + ".ROLE_ADMIN)")
public @interface IsAdmin {
}
