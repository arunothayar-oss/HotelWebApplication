package com.project.hotel.Security;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler  implements AuthenticationSuccessHandler
{
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        for (GrantedAuthority authority :
                authentication.getAuthorities()) {

            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {

                response.sendRedirect(
                        "/FrontEnd/DashBoard.html");

                return;
            }

            if (role.equals("ROLE_STAFF")) {

                response.sendRedirect(
                        "/FrontEnd/OrderEntry.html");

                return;
            }
        }

        response.sendRedirect(
                "/FrontEnd/login.html");
    }
}
