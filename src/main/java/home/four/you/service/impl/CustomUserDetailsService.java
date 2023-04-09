package home.four.you.service.impl;

import home.four.you.repository.UserRepository;
import home.four.you.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Custom implementation of {@link UserDetailsService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Fetching user with email {}", username);

        return userRepository.findByEmail(username)
                .map(user -> new UserPrincipal()
                        .setId(user.getId())
                        .setEmail(user.getEmail())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setAuthorities(user.getAuthorities())
                        .setRole(user.getRole().getName()))
                .orElseThrow(() -> new UsernameNotFoundException(format("User %s not found.", username)));
    }
}
