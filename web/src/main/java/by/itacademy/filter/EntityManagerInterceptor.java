package by.itacademy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Project KR. Created by masiuk-l on 13.10.2017.
 */
@WebFilter(urlPatterns = {"/frontController"})
public class EntityManagerInterceptor implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HibernateUtil.getEntityManager();
//        chain.doFilter(req, res);
//        HibernateUtil.closeEntityManager();

    }
}
