package com.example.ex2_todo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class LatencyLoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long currentTime = System.currentTimeMillis();
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(request, response);
        System.out.printf("[%s - %s] Latency : %s ms\n", request.getMethod(), request.getRequestURI(), (System.currentTimeMillis() - currentTime));
    }
}
