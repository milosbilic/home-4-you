package home.four.you.service.impl;

import home.four.you.model.entity.User;
import home.four.you.repository.UserRepository;
import home.four.you.security.auth.GoogleUserInfo;
import home.four.you.service.RoleService;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static home.four.you.security.auth.authorization.AuthorityRole.ROLE_USER;

/**
 * Implementation of {@link UserService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public User getById(Long id) {
        log.info("Getting user reference by id {}", id);

        return userRepository.getReferenceById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Finding user with email {}", email);

        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(GoogleUserInfo googleUserInfo) {
        log.info("Creating user {}", googleUserInfo.getEmail());

        var user = new User()
                .setEmail(googleUserInfo.getEmail())
                .setFirstName(googleUserInfo.getFirstName())
                .setLastName(googleUserInfo.getLastName())
                .setRole(roleService.findByName(ROLE_USER));

        return userRepository.save(user);
    }
}
