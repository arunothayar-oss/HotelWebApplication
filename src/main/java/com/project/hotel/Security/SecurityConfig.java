package com.project.hotel.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public CustomLoginSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Public pages and APIs
                .requestMatchers(

                        "/",

                        // Frontend pages
                        "/FrontEnd/home.html",
                        "/FrontEnd/AboutUs.html",
                        "/FrontEnd/Feedback.html",
                        "/FrontEnd/Register.html",
                        "/FrontEnd/ForgotPassword.html",
                        "/FrontEnd/login.html",

                        // Static resources
                        "/FrontEnd/*.css",
                        "/FrontEnd/*.js",
                        "/FrontEnd/*.jpg",
                        "/FrontEnd/*.jpeg",
                        "/FrontEnd/*.png",

                        // Public APIs
                        "/api/register",
                        "/api/feedback",
                        "/api/forgot-password",
                          "/forgotpassword"

                ).permitAll()

                // ADMIN only
                .requestMatchers(
                        "/FrontEnd/DashBoard.html",
                        "/FrontEnd/Billing.html")
                .hasRole("ADMIN")

                // ADMIN and STAFF
                .requestMatchers(
                        "/FrontEnd/OrderEntry.html")
                .hasAnyRole("ADMIN", "STAFF")

                .anyRequest()
                .authenticated()
            )

            .formLogin(form -> form

                .loginPage("/FrontEnd/login.html")

                .loginProcessingUrl("/perform_login")

                .usernameParameter("username")

                .passwordParameter("password")

                .successHandler(successHandler())

                .failureUrl(
                        "/FrontEnd/login.html?error=true")

                .permitAll()
            )

            .logout(logout -> logout

                .logoutUrl("/logout")

                .logoutSuccessUrl(
                        "/FrontEnd/login.html")

                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}