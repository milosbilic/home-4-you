package home.four.you.validation;

import home.four.you.model.dto.CreateUserRequestDto;
import jakarta.validation.ConstraintValidatorContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link PasswordMatchValidator}.
 */
@ExtendWith(MockitoExtension.class)
class PasswordMatchValidatorTest {

    PasswordMatchValidator validator;

    @Mock
    CreateUserRequestDto dto;

    @Mock
    ConstraintValidatorContext context;

    @BeforeEach
    void setup() {
        validator = new PasswordMatchValidator();
    }

    @Test
    @DisplayName("Is valid - true")
    void isValid_true() {
        String password = make();

        when(dto.password()).thenReturn(password);
        when(dto.repeatedPassword()).thenReturn(password);

        assertThat(validator.isValid(dto, context)).isTrue();
    }

    @Test
    @DisplayName("Is valid - false")
    void isValid_false() {
        when(dto.password()).thenReturn(make());
        when(dto.repeatedPassword()).thenReturn(make());

        assertThat(validator.isValid(dto, context)).isFalse();
    }
}
