package home.four.you.repository;

import home.four.you.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user by provided username.
     *
     * @param username Username.
     * @return User.
     */
    User findByUsername(String username);

    /**
     * Finds user by provided email.
     *
     * @param email User's email.
     * @return User.
     */
    User findByEmail(String email);
}
