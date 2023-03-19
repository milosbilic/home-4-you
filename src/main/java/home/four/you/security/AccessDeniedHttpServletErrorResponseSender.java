package home.four.you.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.four.you.exception.handler.ErrorResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static home.four.you.exception.ErrorCode.ACCESS_DENIED;

/**
 * Helper class used for sending error response when user access is denied.
 */
@RequiredArgsConstructor
public class AccessDeniedHttpServletErrorResponseSender {

    private final HttpServletResponse response;

    /**
     * Sends error UNAUTHORIZED status code with ACCESS_DENIED error response to servlet output
     * stream.
     *
     * @throws IOException In case of write error to {@link ServletOutputStream}
     */
    public void sendError() throws IOException {
        String serialized = new ObjectMapper()
                .writeValueAsString(new ErrorResponse(ACCESS_DENIED, "ACCESS_DENIED"));
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().write(serialized.getBytes());
    }
}
