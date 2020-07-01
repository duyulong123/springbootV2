package com.springboot.v1.configure.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfigure {
 
    @Bean 
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestReplacedRegistration() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> registration = new FilterRegistrationBean<HttpServletRequestWrapperFilter>();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("httpServletRequestWrapperFilter");
        registration.setOrder(1);
        return registration;
    }
}