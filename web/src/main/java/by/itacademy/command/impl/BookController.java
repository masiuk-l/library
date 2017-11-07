package by.itacademy.command.impl;

import by.itacademy.BookService;
import by.itacademy.ReaderService;
import by.itacademy.command.Controller;
import by.itacademy.entities.Book;
import by.itacademy.impl.BookServiceImpl;
import by.itacademy.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class BookController implements Controller {

    private BookService bookService = BookServiceImpl.getInstance();
    private ReaderService readerService = ReaderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int bookID = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.get(bookID);
        req.getSession().setAttribute("book", book);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
