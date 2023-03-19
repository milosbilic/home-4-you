package home.four.you.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Rest authentication entry point handler.
 */
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * This is invoked when user tries to access a secured REST resource without supplying any
     * credentials. We should just send a 401 Unauthorized response because there is no 'login page'
     * to redirect to.
     *
     * @param request       HTTP Request
     * @param response      HTTP Response
     * @param authException AuthenticationException
     * @throws IOException IOException
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("Responding with unauthorized error. Message - {}", authException.getMessage());

        new AccessDeniedHttpServletErrorResponseSender(response).sendError();
    }
}
