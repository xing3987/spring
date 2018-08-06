package com.example.demo.config;


import com.sun.net.httpserver.HttpExchange;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    add filter
 */
@Configuration
public class WebConfiguration {
    @Bean
    public FilterRegistrationBean remotelpFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        return registration;
    }

    public class MyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request=(HttpServletRequest)servletRequest;
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            System.out.println("this is my Filter:"+request.getRequestURI());
            //if(request.getRequestURI().equals("/hello")) {
            //   // response.sendRedirect(request.getContextPath() + "/getUser");
            //    response.sendRedirect("http://www.baidu.com");
            //}
            //use to session check
            //String user=(String)request.getSession().getAttribute("user");
            //if (null==user){
            //    response.sendRedirect(request.getContextPath() + "/hello");
            //}
            //encoding filter
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            filterChain.doFilter(servletRequest,servletResponse);  //past to another filter
        }

        @Override
        public void destroy() {

        }
    }
}
