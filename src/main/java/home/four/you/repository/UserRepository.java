package home.four.you.repository;

import home.four.you.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA repository for {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user by provided email.
     *
     * @param email User's email.
     * @return Matching user or empty {@link Optional}.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with provided email exists.
     *
     * @param email User's email
     * @return True if user exists, otherwise false.
     */
    boolean existsByEmail(String email);
}
