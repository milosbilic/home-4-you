package home.four.you.model.dto;

import home.four.you.security.auth.authorization.AuthorityRole;
import home.four.you.validation.PasswordMatches;
import home.four.you.validation.UserRequestChecks;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO representing the create user request.
 */
@PasswordMatches(groups = UserRequestChecks.class)
@GroupSequence({CreateUserRequestDto.class, UserRequestChecks.class})
public record CreateUserRequestDto(@NotBlank @Email String email,
                                   @NotBlank String firstName,
                                   @NotBlank String lastName,
                                   @NotBlank String password,
                                   @NotBlank String repeatedPassword,
                                   @NotBlank String phone,
                                   @NotNull AuthorityRole role) {
}
