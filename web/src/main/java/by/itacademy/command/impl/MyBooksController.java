package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;
import by.itacademy.service.BookService;
import by.itacademy.service.FormService;
import by.itacademy.service.impl.BookServiceImpl;
import by.itacademy.service.impl.FormServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public class MyBooksController implements Controller {
    private BookService bookService = BookServiceImpl.getInstance();
    private FormService formService = FormServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("sreader") == null) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=error");
        }
        Reader reader = (Reader) req.getSession().getAttribute("sreader");
        ArrayList<Form> forms = new ArrayList<>(formService.getByReader(reader));
        req.getSession().setAttribute("forms", forms);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
