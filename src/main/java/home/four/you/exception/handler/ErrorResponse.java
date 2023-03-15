package home.four.you.exception.handler;

import home.four.you.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Model for exception response.
 *
 * @author Branko Ostojic (bostojic@itekako.com)
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    protected ErrorCode code;
    protected String message;

    public ErrorResponse() {
        this.code = ErrorCode.UNDEFINED;
    }
}
