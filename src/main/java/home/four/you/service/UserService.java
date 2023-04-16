package home.four.you.service;

import home.four.you.model.dto.CreateUserRequestDto;
import home.four.you.model.entity.User;
import home.four.you.security.auth.GoogleUserInfo;

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
     * @return Matching user.
     */
    User findById(Long id);
}
