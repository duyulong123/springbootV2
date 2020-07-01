package com.springboot.v1.configure.interceptor;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName: HttpServletRequestWrapperFilter
 * @Description: filter
 * @author dyl
 * @date 2020-3-16 7:14:45
 */
public class HttpServletRequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ServletRequest requestWrapper = null;

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String methodType = httpRequest.getMethod();
            if ("POST".equalsIgnoreCase(methodType)) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper(
                        (HttpServletRequest) request);
            }
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }
}