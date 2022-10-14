package com.kildeen.allstreams;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebFilter("/test/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Filtering this shit");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}