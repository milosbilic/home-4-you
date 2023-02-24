package home.four.you.service;

import home.four.you.model.entity.User;

import java.util.List;

/**
 * Service for {@link User} entity related operations.
 */
public interface UserService {

    /**
     * Finds user by provided ID.
     *
     * @param id User ID.
     * @return User.
     */
    User findById(Long id);
}
