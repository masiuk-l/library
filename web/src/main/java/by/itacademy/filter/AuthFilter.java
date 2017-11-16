package by.itacademy.filter;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 16.08.2017.
 *
 * Redirects unauthorized requests to login page
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        ArrayList<String> readerOnly = new ArrayList<>();
        readerOnly.add("/reader/edit/");
        readerOnly.add("/book/mybooks");

        ArrayList<String> librarianOnly = new ArrayList<>();
        librarianOnly.add("/book/add");
        librarianOnly.add("/author/add");
        librarianOnly.add("/reader/ban/");
        librarianOnly.add("/book/edit/");
        librarianOnly.add("/book/delete/");
        librarianOnly.add("/reader/all");


        String url = request.getRequestURL().toString();
        if (url.contains("/book/reserve") || url.contains("/book/return")) {
            if ((session.getAttribute("sreader") == null)) {
                PrintWriter writer = resp.getWriter();
                writer.print(new Gson().toJson("Auth required"));
                return;
            }
        } else if (matcher(url, readerOnly)) {
            if ((session.getAttribute("sreader") == null)) {
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/login/");
                return;
            }
        } else if (matcher(url, librarianOnly)) {
            if ((session.getAttribute("slibrarian") == null)) {
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/login/");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private boolean matcher(String url, ArrayList<String> paths) {
        for (String path : paths) {
            if (url.contains(path))
                return true;
        }
        return false;
    }

}
