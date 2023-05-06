package home.four.you.service.impl;

import home.four.you.exception.BadRequestException;
import home.four.you.model.dto.CreateUserRequestDto;
import home.four.you.model.entity.User;
import home.four.you.repository.UserRepository;
import home.four.you.security.auth.GoogleUserInfo;
import home.four.you.service.RoleService;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static home.four.you.exception.ErrorMessage.USER_EXISTS;
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
    private final PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional
    public User createUser(CreateUserRequestDto dto) {
        log.info("Creating user [{}]", dto);

        if (userRepository.existsByEmail(dto.email())) {
            throw new BadRequestException(USER_EXISTS);
        }

        var user = new User()
                .setEmail(dto.email())
                .setFirstName(dto.firstName())
                .setLastName(dto.lastName())
                .setPassword(passwordEncoder.encode(dto.password()))
                .setPhone(dto.phone()) //TODO Drop unique constraint?
                .setRole(roleService.findByName(dto.role()));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("Finding user {}", id);

        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting user {}", id);

        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.info("Finding all users for page {}", pageable.getPageNumber());

        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        log.info("Finding by user with email {}", email);

        return userRepository.findByEmailAndPassword(email, password);
    }
}
