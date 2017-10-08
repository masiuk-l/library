package by.itacademy.command.impl;

import by.itacademy.BookService;
import by.itacademy.command.Controller;
import by.itacademy.entities.Book;
import by.itacademy.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class SearchCatalogController implements Controller {
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        if (name.length() < 3 || name.length() > 30) {
            req.getSession().setAttribute("bookVOS", null);
            req.getSession().setAttribute("Msg", "Invalid input");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        } else {
            ArrayList<Book> books = new ArrayList<>(bookService.searchByName(name));
            if (books.isEmpty()) {
                req.getSession().setAttribute("bookVOS", null);
                req.getSession().setAttribute("Msg", "No books match your input");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            } else {

                req.getSession().setAttribute("books", books);
                req.getSession().setAttribute("Msg", "");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            }

        }
    }
}
