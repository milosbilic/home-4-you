package home.four.you.security;

import home.four.you.exception.TokenAuthenticationFilterException;
import home.four.you.service.impl.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

/**
 * Filter for processing authentication token.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            var jwtToken = getJwtFromRequest(request)
                    .orElseThrow(TokenAuthenticationFilterException::new);

            if (tokenProvider.validateToken(jwtToken)) {
                processUserToken(request, jwtToken);
            } else {
                log.error("Invalid JWT token {}", jwtToken);

                throw new TokenAuthenticationFilterException();
            }

            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException | TokenAuthenticationFilterException e) {
            new AccessDeniedHttpServletErrorResponseSender(response).sendError();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        var uri = request.getRequestURI();

        return uri.equals("/")
                || requestMatches(GET, "/ads", request);
    }

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {
        return ofNullable(request.getHeader("Authorization"))
                .filter(StringUtils::hasText)
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7));
    }

    private void processUserToken(HttpServletRequest request, String jwtToken) {
        var userPrincipal = getUserPrincipal(jwtToken);
        processAuthentication(request, userPrincipal);
    }

    private UserPrincipal getUserPrincipal(String jwtToken) {
        var userName = tokenProvider.getUsernameFromToken(jwtToken);

        return customUserDetailsService.loadUserByUsername(userName);
    }

    private void processAuthentication(HttpServletRequest request, UserPrincipal userPrincipal) {
        var authentication = new UsernamePasswordAuthenticationToken(
                userPrincipal, null, userPrincipal.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        getContext().setAuthentication(authentication);
    }

    private boolean requestMatches(HttpMethod method, String uri, HttpServletRequest request) {
        return method.name().equals(request.getMethod()) && request.getRequestURI().startsWith(uri);
    }
}
