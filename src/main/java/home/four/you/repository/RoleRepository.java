package home.four.you.repository;

import home.four.you.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link Role} entity.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(Role.AuthorityRole role);
}
