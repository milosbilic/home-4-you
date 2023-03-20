package home.four.you.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.four.you.exception.handler.ErrorResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.util.UUID;

import static home.four.you.exception.ErrorCode.ACCESS_DENIED;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link RestAuthenticationEntryPoint}.
 */
@ExtendWith(MockitoExtension.class)
class RestAuthenticationEntryPointTest {

    private final String error = UUID.randomUUID().toString();
    private RestAuthenticationEntryPoint entryPoint;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException authException;

    @BeforeEach
    void setup() {
        entryPoint = new RestAuthenticationEntryPoint();
        when(authException.getMessage()).thenReturn(error);
    }

    @Test
    @DisplayName("Handling authentication exception")
    void commence() throws IOException {
        var outputStream = mock(ServletOutputStream.class);
        String serialized = new ObjectMapper()
                .writeValueAsString(new ErrorResponse(ACCESS_DENIED, "ACCESS_DENIED"));

        when(response.getOutputStream()).thenReturn(outputStream);
        entryPoint.commence(request, response, authException);

        verify(response).setHeader("Content-Type", "application/json");
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
        verify(response).getOutputStream();
        verify(outputStream).write(serialized.getBytes());

        verify(authException, times(1)).getMessage();
        verifyNoMoreInteractions(request, response, authException);
    }

    @Test
    @DisplayName("Handling authentication exception - IO exception on output stream")
    void commenceException_onOutputStream() throws IOException {
        Mockito.doThrow(new IOException()).when(response).getOutputStream();
        assertThrows(IOException.class,
                () -> entryPoint.commence(request, response, authException));
    }

    @Test
    @DisplayName("Handling authentication exception - IO exception on write to output stream")
    void commenceException_onWriteToOutputStream() throws IOException {
        var outputStream = mock(ServletOutputStream.class);

        when(response.getOutputStream()).thenReturn(outputStream);
        Mockito.doThrow(new IOException()).when(outputStream).write(any());

        assertThrows(IOException.class,
                () -> entryPoint.commence(request, response, authException));
    }
}
