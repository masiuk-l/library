package by.itacademy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Project KR. Created by masiuk-l on 16.08.2017.
 *
 * Redirects unauthorized requests to login page
 */
//@WebFilter(filterName = "AuthFilter", urlPatterns = {"/frontController"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//        HttpSession session = request.getSession();
//
//        ArrayList<CommandType> readerOnly = new ArrayList<>();
//        readerOnly.add(CommandType.EDIT_READER);
//        readerOnly.add(CommandType.MY_BOOKS);
//
//        ArrayList<CommandType> librarianOnly = new ArrayList<>();
//        librarianOnly.add(CommandType.ADD_BOOK);
//        librarianOnly.add(CommandType.ADD_AUTHOR);
//        librarianOnly.add(CommandType.BAN_READER_AJAX);
//        librarianOnly.add(CommandType.EDIT_BOOK);
//        librarianOnly.add(CommandType.DELETE_BOOK);
//        librarianOnly.add(CommandType.READERS);
//
//
//        CommandType type = RequestHandler.getCommand(request);
//        if (CommandType.RESERVE_BOOK_AJAX.equals(type) || CommandType.RETURN_BOOK_AJAX.equals(type)) {
//            String contextPath = request.getContextPath();
//            if ((session.getAttribute("sreader") == null)) {
//                PrintWriter writer = resp.getWriter();
//                writer.print(new Gson().toJson("Auth required"));
//                return;
//            }
//        } else if (readerOnly.contains(type)) {
//            if ((session.getAttribute("sreader") == null)) {
//                String contextPath = request.getContextPath();
//                response.sendRedirect(contextPath + "/frontController?command=login");
//                return;
//            }
//        } else if (librarianOnly.contains(type)) {
//            if ((session.getAttribute("slibrarian") == null)) {
//                String contextPath = request.getContextPath();
//                response.sendRedirect(contextPath + "/frontController?command=login");
//                return;
//            }
//        }
//        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
