package home.four.you.service.impl;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.entity.User;
import home.four.you.repository.UserRepository;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link UserService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        log.debug("Finding user with id {}", id);

        return userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
