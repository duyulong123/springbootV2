package com.springboot.v1.configure.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: WebConfiguration
 * @Description: 
 * @author dyl
 * @date 2020-3-11 5:10:15
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean 
    public HandlerInterceptor getMyInterceptor() {
        return new IpControl();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/UfAgent/ag*/*")
        .excludePathPatterns("/")
        .excludePathPatterns("/error","/**/*.*","/favicon.ico","/index.html");
        super.addInterceptors(registry);
    }
    
}
