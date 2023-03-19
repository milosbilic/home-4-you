package home.four.you.configuration;

import home.four.you.security.TokenAuthenticationFilter;
import home.four.you.security.auth.OAuth2AuthenticationFailureHandler;
import home.four.you.security.auth.OAuth2AuthenticationSuccessHandler;
import home.four.you.service.impl.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2AuthenticationSuccessHandler successHandler;
    private final OAuth2AuthenticationFailureHandler failureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/oauth2/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(httpSecurity -> httpSecurity.sessionCreationPolicy(STATELESS))
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(UNAUTHORIZED)))
                .formLogin().disable()
                .httpBasic().disable()
                .oauth2Login(oauth2 -> {
                    oauth2.authorizationEndpoint(endpoint -> endpoint.baseUri("/oauth2/authorize"));
                    oauth2.redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"));
                    oauth2.userInfoEndpoint().userService(customOAuth2UserService);
                    oauth2.successHandler(successHandler);
                    oauth2.failureHandler(failureHandler);
                })
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
