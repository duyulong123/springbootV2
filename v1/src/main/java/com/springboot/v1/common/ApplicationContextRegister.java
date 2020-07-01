package com.springboot.v1.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: ApplicationContextRegister
 * @Description: 应用上下文
 * @author dyl
 * @date 2020年7月1日 下午2:53:31
 */

@Component
@Lazy(value = true)
@Slf4j
public class ApplicationContextRegister implements ApplicationContextAware {

    private static ApplicationContext context;

    //设置上下文
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("设置context的值-->{}", applicationContext);
        context = applicationContext;

    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    //获取请求对象
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return request != null ? request.getRequest() : (HttpServletRequest) request;

    }

    //获取返回对象
    public static HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes response = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return response != null ? response.getResponse() : (HttpServletResponse) response;
    }
}
