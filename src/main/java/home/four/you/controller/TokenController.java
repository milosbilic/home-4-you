package home.four.you.controller;

import home.four.you.model.entity.User;
import home.four.you.security.TokenProvider;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenProvider tokenProvider;
    private final UserService userService;

    @GetMapping("{id}")
    String getToken(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow();
        return tokenProvider.createToken(user);
    }
}
