package com.foodapp.Auth_Service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection since we are building a stateless REST API
                .csrf(csrf -> csrf.disable())

                // Configure route authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access the register and login routes without a password
                        .requestMatchers("/api/auth/**").permitAll()
                        // Any other request across this service must be authenticated
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
