package home.four.you.exception.handler;

import home.four.you.exception.Home4YouException;
import home.four.you.exception.ResourceNotFoundException;
import home.four.you.exception.ValidationErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static home.four.you.exception.ErrorCode.VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.util.StringUtils.isEmpty;

@ControllerAdvice
@RestController
@Getter
@Slf4j
@RequiredArgsConstructor
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                         WebRequest request) {
        log.info("Handling resource not found exception {}", ex.getMessage());

        var response = new ErrorResponse(ex.getCode(), ex.getCode().name());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(Home4YouException.class)
    public ResponseEntity<ErrorResponse> handleHome4YouException(Home4YouException ex,
                                                                 WebRequest request) {
        log.error("Handling Home 4 You  exception: {}", ex.getMessage());

        var message = isEmpty(ex.getMessage()) ? ex.getCode().name() : ex.getMessage();
        var exceptionResponse = new ErrorResponse(ex.getCode(), message);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        var errors = ex.getBindingResult().getAllErrors();
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ValidationErrorResponse(VALIDATION_ERROR.name(), errors));
    }

}
