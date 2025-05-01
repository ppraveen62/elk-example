package com.app.client1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.app.client1.ApiLoggingFilter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<ApiLoggingFilter> loggingFilter() {
        FilterRegistrationBean<ApiLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiLoggingFilter());
        registrationBean.addUrlPatterns("/api/*"); // You can adjust the URL pattern as needed
        return registrationBean;
    }
}
