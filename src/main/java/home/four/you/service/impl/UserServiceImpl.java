package home.four.you.service.impl;

import home.four.you.exception.EmailExistsException;
import home.four.you.model.dto.UserDto;
import home.four.you.model.entity.Role;
import home.four.you.model.entity.User;
import home.four.you.repository.RoleRepository;
import home.four.you.repository.UserRepository;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link UserService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        log.debug("Finding all users");

        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        log.debug("Finding user with id {}", id);

        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        log.debug("Saving user [{}]", user);

        return userRepository.save(user);
    }

    @Override
    public User save(UserDto user) throws EmailExistsException {
        log.debug("Saving user [{}]", user);

        if (emailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with this email: "
                    + user.getEmail());
        }
        var registered = new User();
//        User registered = toEntity.convertMinimum(user);
//        registered.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(2L).orElseThrow());
        registered.setRoles(roles);
        return userRepository.save(registered);
    }

    @Override
    public void delete(User user) {
        log.debug("Deleting user [{}]", user);

        userRepository.delete(user);
    }

    @Override
    public User createUserAccount(UserDto userDto) {
        log.debug("Creating user [{}]", userDto);

        User registered = null;
        try {
            registered = save(userDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
