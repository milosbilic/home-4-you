package home.four.you.helper.validation.annotation;

import home.four.you.helper.validation.ImageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Image {
	String message() default "Not an image!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
