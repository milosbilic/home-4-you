package home.four.you.service;

import home.four.you.exception.EmailExistsException;
import home.four.you.model.dto.UserDto;
import home.four.you.model.entity.User;

import java.util.List;

/**
 * Service for {@link User} entity related operations.
 */
public interface UserService {

    /**
     * Finds all users.
     *
     * @return List of all users.
     */
    List<User> findAll();

    /**
     * Saves a user.
     *
     * @param user User.
     * @return User.
     */
    User save(User user);

    /**
     * Saves a user.
     *
     * @param user User.
     * @return User.
     */
    User save(UserDto user) throws EmailExistsException;

    /**
     * Deletes a user.
     *
     * @param user User.
     */
    void delete(User user);

    /**
     * Finds user by provided ID.
     *
     * @param id User ID.
     * @return User.
     */
    User findById(Long id);

    /**
     * Creates a user account with specified details.
     *
     * @param userDto User details.
     * @return Created user.
     */
    User createUserAccount(UserDto userDto);

    /**
     * Finds a user by provided email.
     *
     * @param email User's email.
     * @return User.
     */
    User findByEmail(String email);


}
