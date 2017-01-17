package com.epam.jobmatch.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter{

    private static final String CHARSET = "UTF-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(CHARSET);
        response.setCharacterEncoding(CHARSET);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
