package home.four.you.service.impl;

import home.four.you.model.entity.User;
import home.four.you.repository.AdRepository;
import home.four.you.repository.RoleRepository;
import home.four.you.repository.UserRepository;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        log.debug("Finding all users");

        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        log.debug("Finding user with id {}", id);

        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        log.debug("Saving user [{}]", user);

        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        log.debug("Deleting user [{}]", user);

        userRepository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
