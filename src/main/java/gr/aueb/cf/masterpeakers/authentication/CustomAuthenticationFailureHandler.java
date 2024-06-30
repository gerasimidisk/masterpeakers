package gr.aueb.cf.masterpeakers.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Invalid username or password";

        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "User does not exist";
        }else if ("Username is required".equals(exception.getMessage())) {
            errorMessage = "Username is required";
        } else if ("Password is required".equals(exception.getMessage())) {
            errorMessage = "Password is required";
        } else if ("Both fields are required".equals(exception.getMessage())) {
            errorMessage = "Both username and password are required";
        } else if (exception.getMessage().contains("Bad credentials")) {
            errorMessage = "Wrong password";
        }

        response.sendRedirect("/login?error=" + errorMessage);
    }
}
