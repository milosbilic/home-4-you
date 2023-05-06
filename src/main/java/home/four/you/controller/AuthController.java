package home.four.you.controller;

import home.four.you.model.dto.LoginRequestDto;
import home.four.you.security.auth.EmailAndPasswordAuthManager;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final EmailAndPasswordAuthManager authManager;

    @PostMapping("login")
    public void login(@RequestBody LoginRequestDto dto,
                      HttpServletResponse response) {
        log.debug("Login attempt for {}", dto.email());

        String jwt = authManager.authenticate(dto);

        response.setHeader("Authorization", jwt);
    }
}
