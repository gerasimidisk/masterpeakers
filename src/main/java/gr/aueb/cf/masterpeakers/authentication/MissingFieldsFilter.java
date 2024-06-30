package gr.aueb.cf.masterpeakers.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MissingFieldsFilter implements Filter {

    private final AuthenticationFailureHandler failureHandler;

    public MissingFieldsFilter(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getRequestURI().equals("/login") && httpRequest.getMethod().equalsIgnoreCase("POST")) {
            String username = httpRequest.getParameter("username");
            String password = httpRequest.getParameter("password");

            if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
                failureHandler.onAuthenticationFailure(httpRequest, httpResponse, new AuthenticationException("Both fields are required") {});
                return;
            }

            if (username == null || username.isEmpty()) {
                failureHandler.onAuthenticationFailure(httpRequest, httpResponse, new AuthenticationException("Username is required") {});
                return;
            }

            if (password == null || password.isEmpty()) {
                failureHandler.onAuthenticationFailure(httpRequest, httpResponse, new AuthenticationException("Password is required") {});
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
