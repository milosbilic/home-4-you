package home.four.you.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Password validation annotation.
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatches {


    String message() default "Passwords do not match";

    /**
     * Gets groups.
     *
     * @return Value.
     */
    Class<?>[] groups() default {};

    /**
     * Gets payloads.
     *
     * @return Payloads.
     */
    Class<? extends Payload>[] payload() default {};
//
//    String password();
//
//    String repeatedPassword();
}
