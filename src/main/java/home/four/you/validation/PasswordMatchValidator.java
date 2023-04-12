package home.four.you.validation;

import home.four.you.model.dto.CreateUserRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for password matching during user creation.
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatches, CreateUserRequestDto> {

    @Override
    public boolean isValid(CreateUserRequestDto dto, ConstraintValidatorContext context) {
        return dto.password().equals(dto.repeatedPassword());
    }
}
