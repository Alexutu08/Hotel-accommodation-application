package com.example.accommodationmanagement.config;

import com.example.accommodationmanagement.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/register", "/login", "/users", "/css/**", "/js/**", "/images/**").permitAll() // Endpoint-uri accesibile public
//                                .requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("SUPERADMIN", "USER") // Endpoint-uri accesibile la GET pentru toți utilizatorii autentificați
//                                .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("SUPERADMIN") // Endpoint-uri accesibile doar SuperAdmin la DELETE
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/dashboard")
                                .failureHandler((request, response, exception) -> {
                                    response.sendRedirect("/login?error");
                                })
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                )
                .userDetailsService(userDetailsService())
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity in testing

        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        return new HttpSessionCsrfTokenRepository();
//    }
}