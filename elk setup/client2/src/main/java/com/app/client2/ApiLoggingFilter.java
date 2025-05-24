package com.app.client2;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@WebFilter("/*") // This ensures the filter applies to all requests.
public class ApiLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            MDC.put("api_url", httpRequest.getRequestURI());
            MDC.put("http_method", httpRequest.getMethod());
            MDC.put("http_status", String.valueOf(httpResponse.getStatus()));
            MDC.put("http_duration_ms", String.valueOf(duration));
            logger.info("API_Summary: Method={} Path={} Status={} Duration={}ms",
                    httpRequest.getMethod(),
                    httpRequest.getRequestURI(),
                    httpResponse.getStatus(),
                    duration);
            MDC.clear(); // Always clean up
        }
    }

    @Override
    public void destroy() {
    }
}
