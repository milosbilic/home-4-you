package home.four.you.service;

import home.four.you.model.dto.CreateUserRequestDto;
import home.four.you.model.entity.User;
import home.four.you.security.auth.GoogleUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service for {@link User} entity related operations.
 */
public interface UserService {

    /**
     * Gets user by provided ID.
     *
     * @param id User ID.
     * @return User.
     */
    User getById(Long id);

    /**
     * Finds a user by provided email.
     *
     * @param email User's email.
     * @return Matching user, or empty {@link Optional}.
     */
    Optional<User> findByEmail(String email);

    /**
     * Creates new user with specified google details.
     *
     * @param googleUserInfo User's google info.
     * @return Created user.
     */
    User createUser(GoogleUserInfo googleUserInfo);

    /**
     * Creates new user with specified details.
     *
     * @param dto Create user request DTO.
     * @return Created user.
     */
    User createUser(CreateUserRequestDto dto);

    /**
     * Finds user by provided ID.
     *
     * @param id User ID.
     * @return Matching user, or empty {@link Optional}.
     */
    Optional<User> findById(Long id);

    /**
     * Deletes user by provided ID.
     *
     * @param id User ID.
     */
    void delete(Long id);

    /**
     * Finds all users and returns a selected page.
     *
     * @param pageable Pageable.
     * @return Page of users.
     */
    Page<User> findAll(Pageable pageable);
}
