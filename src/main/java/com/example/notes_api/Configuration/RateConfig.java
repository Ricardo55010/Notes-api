package com.example.notes_api.Configuration;

import com.example.notes_api.filters.RateLimiterFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateConfig {
    @Bean
    public FilterRegistrationBean<RateLimiterFilter> rateLimitingFilter() {
        FilterRegistrationBean<RateLimiterFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimiterFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}