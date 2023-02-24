package home.four.you.service;

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
     * Finds a user by provided email.
     *
     * @param email User's email.
     * @return User.
     */
    User findByEmail(String email);


}
