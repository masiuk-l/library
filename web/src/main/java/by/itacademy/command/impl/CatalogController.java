package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Book;
import by.itacademy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 25.08.2017.
 */
public class CatalogController implements Controller {
    @Autowired
    private BookService bookService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        int pageSize = req.getParameter("pageSize") == null ? 3 : Integer.parseInt(req.getParameter("pageSize"));
        ArrayList<Book> books = new ArrayList<>(bookService.getCatalogPage(pageNumber, pageSize));

        req.getSession().setAttribute("Msg", "");
        req.getSession().setAttribute("books", books);
        int pageCount = (int) Math.ceil(bookService.getAll().size() / (double) pageSize);
        req.getSession().setAttribute("pageCount", pageCount);
        req.getSession().setAttribute("pageNumber", pageNumber);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
