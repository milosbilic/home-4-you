package home.four.you.security.auth;

import home.four.you.model.dto.LoginRequestDto;
import home.four.you.security.TokenProvider;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.springframework.security.oauth2.core.OAuth2ErrorCodes.ACCESS_DENIED;

/**
 * Class used for authenticating user with their provided email and password.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class EmailAndPasswordAuthManager {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginRequestDto loginRequest) {
        log.debug("Authenticating user {}", loginRequest.email());

        var user = userService.findByEmail(loginRequest.email())
                .orElseThrow(() -> new AccessDeniedException(ACCESS_DENIED));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new AccessDeniedException(ACCESS_DENIED);
        }

        return tokenProvider.createToken(user);
    }
}
