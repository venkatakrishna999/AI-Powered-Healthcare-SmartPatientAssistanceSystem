package com.vcube.healthcare.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    // CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
            List.of("https://healthcare-frontend-dxym.onrender.com")
        );

        configuration.setAllowedMethods(
            List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        );

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .cors(Customizer.withDefaults())

            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Allow CORS preflight requests
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Public APIs
                .requestMatchers(
                    "/api/auth/login",
                    "/api/users/register",
                    "/v3/api-docs/**",
                    "/v3/api-docs",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()

                // Only ADMIN can add doctors
                .requestMatchers(HttpMethod.POST, "/api/doctors")
                .hasRole("ADMIN")

                // Only ADMIN can update doctors
                .requestMatchers(HttpMethod.PUT, "/api/doctors/**")
                .hasRole("ADMIN")

                // Only ADMIN can delete doctors
                .requestMatchers(HttpMethod.DELETE, "/api/doctors/**")
                .hasRole("ADMIN")

                // All authenticated users can view doctors
                .requestMatchers("/api/doctors/**")
                .authenticated()

                // Only ADMIN can view all users
                .requestMatchers(HttpMethod.GET, "/api/users")
                .hasRole("ADMIN")

                // Only ADMIN can update users
                .requestMatchers(HttpMethod.PUT, "/api/users/**")
                .hasRole("ADMIN")

                // Only ADMIN can delete users
                .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                .hasRole("ADMIN")

                // Authenticated users can access other user endpoints
                .requestMatchers("/api/users/**")
                .authenticated()

                // All remaining APIs require authentication
                .anyRequest().authenticated()
            )

            .exceptionHandling(exception -> exception

                .authenticationEntryPoint((request, response, authException) ->
                    response.setStatus(401)
                )

                .accessDeniedHandler((request, response, accessDeniedException) ->
                    response.setStatus(403)
                )
            )

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authenticationProvider(authenticationProvider())

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
