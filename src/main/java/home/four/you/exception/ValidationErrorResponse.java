package home.four.you.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public record ValidationErrorResponse(String message, List<ObjectError> details) {
}
