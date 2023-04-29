package home.four.you.controller;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.dto.CreateUserRequestDto;
import home.four.you.model.dto.CreateUserResponseDto;
import home.four.you.model.dto.UserBriefDetailsDto;
import home.four.you.model.dto.UserDetailsDto;
import home.four.you.model.entity.User;
import home.four.you.security.auth.authorization.permission.CanDeleteUser;
import home.four.you.security.auth.authorization.permission.IsAdmin;
import home.four.you.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller used for {@link User} resource API operations.
 */
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto dto) {
        log.info("Creating user [{}]", dto);

        var user = userService.createUser(dto);

        return conversionService.convert(user, CreateUserResponseDto.class);
    }

    @GetMapping("{id}")
    public UserDetailsDto details(@PathVariable Long id) {
        log.info("Getting details for user {}", id);

        var user = userService.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return conversionService.convert(user, UserDetailsDto.class);
    }

    @DeleteMapping("{id}")
    @CanDeleteUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        log.info("Deleting user {}", id);

        userService.delete(id);
    }

    @GetMapping
    @IsAdmin
    public Page<UserBriefDetailsDto> getAllUsers(@PageableDefault Pageable pageable) {
        log.info("Getting all users");

        return userService.findAll(pageable)
                .map(user -> conversionService.convert(user, UserBriefDetailsDto.class));
    }
}
