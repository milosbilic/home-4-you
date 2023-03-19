package home.four.you.service;

import home.four.you.model.entity.Role;
import home.four.you.security.auth.authorization.AuthorityRole;

/**
 * Service for {@link Role} entity related operations.
 */
public interface RoleService {

    /**
     * Finds role by provided name.
     *
     * @param name Role name.
     * @return Matching role.
     */
    Role findByName(AuthorityRole name);
}
