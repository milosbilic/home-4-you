package home.four.you.security;

import home.four.you.service.impl.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

import static home.four.you.TestUtil.generateId;
import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

/**
 * Unit tests for {@link TokenAuthenticationFilter}.
 */
@ExtendWith(MockitoExtension.class)
class TokenAuthenticationFilterTest {

    TokenAuthenticationFilter filter;

    @Mock
    TokenProvider tokenProvider;

    @Mock
    CustomUserDetailsService customUserDetailsService;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    ServletOutputStream outStream;

    @Mock
    FilterChain filterChain;

    final String AUTH_HEADER = "Authorization";

    final String BEARER_PREFIX = "Bearer ";

    @BeforeEach
    void setUp() {
        filter = new TokenAuthenticationFilter(tokenProvider, customUserDetailsService);
    }

    @Test
    @DisplayName("No Authorization")
    void noAuthorization() throws ServletException, IOException {
        when(request.getHeader(AUTH_HEADER)).thenReturn(null);
        when(response.getOutputStream()).thenReturn(outStream);

        filter.doFilterInternal(request, response, filterChain);

        verifyNoInteractions(tokenProvider, customUserDetailsService);
        verifyNoMoreInteractions(filterChain);
        assertThat(getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("Empty Authorization")
    void emptyAuthorization() throws ServletException, IOException {
        when(request.getHeader(AUTH_HEADER)).thenReturn("");
        when(response.getOutputStream()).thenReturn(outStream);

        filter.doFilterInternal(request, response, filterChain);

        verifyNoInteractions(tokenProvider, customUserDetailsService);
        assertThat(getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("Not Bearer token Authorization")
    void notBearerTokenAuthorization() throws ServletException, IOException {
        when(request.getHeader(AUTH_HEADER)).thenReturn(make());
        when(response.getOutputStream()).thenReturn(outStream);

        filter.doFilterInternal(request, response, filterChain);

        verifyNoInteractions(tokenProvider, customUserDetailsService);
        assertThat(getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("Invalid token")
    void invalidToken() throws ServletException, IOException {
        String token = make();

        when(request.getHeader(AUTH_HEADER)).thenReturn(BEARER_PREFIX + token);
        when(tokenProvider.validateToken(token)).thenReturn(false);
        when(response.getOutputStream()).thenReturn(outStream);

        filter.doFilterInternal(request, response, filterChain);

        verifyNoInteractions(customUserDetailsService);
        verifyNoMoreInteractions(tokenProvider);
        assertThat(getContext().getAuthentication()).isNull();
    }

    @Test
    @Tag("doNotVerify")
    @DisplayName("No user - exception")
    void noUser_exception() throws ServletException, IOException {
        String token = make();
        String username = make();
        var outputStream = mock(ServletOutputStream.class);

        when(request.getHeader(AUTH_HEADER)).thenReturn(BEARER_PREFIX + token);
        when(response.getOutputStream()).thenReturn(outputStream);
        when(tokenProvider.validateToken(token)).thenReturn(true);
        when(tokenProvider.getUsernameFromToken(token)).thenReturn(username);
        when(customUserDetailsService.loadUserByUsername(username)).thenThrow(
                new UsernameNotFoundException(make()));

        filter.doFilterInternal(request, response, filterChain);

        assertThat(getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("Authenticated - user")
    void authenticated_user() throws ServletException, IOException {
        String token = make();
        var userPrincipal = UserPrincipal.builder()
                .id(generateId())
                .email(make())
                .build();
        when(request.getHeader(AUTH_HEADER)).thenReturn(BEARER_PREFIX + token);
        when(tokenProvider.validateToken(token)).thenReturn(true);
        when(tokenProvider.getUsernameFromToken(token)).thenReturn(userPrincipal.getEmail());
        when(customUserDetailsService.loadUserByUsername(userPrincipal.getEmail()))
                .thenReturn(userPrincipal);

        filter.doFilterInternal(request, response, filterChain);

        assertThat(getContext().getAuthentication()).isNotNull();
        var principal = (UserPrincipal) getContext().getAuthentication().getPrincipal();

        assertThat(principal).isEqualTo(userPrincipal);
    }
}
