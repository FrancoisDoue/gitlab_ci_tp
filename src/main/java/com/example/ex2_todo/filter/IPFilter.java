package com.example.ex2_todo.filter;

import jakarta.persistence.Access;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class IPFilter extends OncePerRequestFilter {

    @Value("${api.ips}") // chaine de charactère d'ips séparé de ','
    private String ips;

    @Autowired
    private AuthenticationEntryPoint entryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            List<String> authorisations = List.of(ips.split(","));
            authorisations.stream().filter(a -> a.equals(request.getRemoteAddr())).findAny().orElseThrow(
                    () -> new AuthenticationException("Invalid IP address") {}
            );
            System.out.println(request.getRemoteAddr());
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            entryPoint.commence(request, response, e);
        }
    }
}
