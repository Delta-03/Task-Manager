package com.unikl.taskmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Fulfills password hashing requirement (ASVS V3)
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Keeping disabled for rapid runtime testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/h2-console/**").permitAll()
                .anyRequest().authenticated() // All other features require active session login
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/tasks", true)
                .permitAll()
            )
            .logout(logout -> logout.logoutSuccessUrl("/login"))
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Required for H2 browser console view
            
        return http.build();
    }
}