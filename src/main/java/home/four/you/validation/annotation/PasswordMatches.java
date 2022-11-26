package home.four.you.validation.annotation;

import home.four.you.validation.PasswordMatchesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches { 
    String message() default "Passwords don't match";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
