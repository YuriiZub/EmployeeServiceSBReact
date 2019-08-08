package com.departments.depthdemo;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS Filter
 *
 * This filter is an implementation of W3C's CORS
 * (Cross-Origin Resource Sharing) specification,
 * which is a mechanism that enables cross-origin requests.
 */
public class CORSFilter extends GenericFilterBean implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        chain.doFilter(request, response);
    }

}
