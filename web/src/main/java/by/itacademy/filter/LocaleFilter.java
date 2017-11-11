package by.itacademy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Project KR. Created by masiuk-l on 16.08.2017.
 *
 */
//@WebFilter(urlPatterns = {"/frontController"})
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String locale = servletRequest.getParameter("locale");
        if (locale != null && !locale.isEmpty()) {
            ((HttpServletRequest) servletRequest).getSession().setAttribute("locale", locale);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
