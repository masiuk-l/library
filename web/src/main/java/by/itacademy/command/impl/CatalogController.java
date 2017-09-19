package by.itacademy.command.impl;

import by.itacademy.BookVO;
import by.itacademy.Book;
import by.itacademy.BookService;
import by.itacademy.impl.BookServiceImpl;
import by.itacademy.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 25.08.2017.
 */
public class CatalogController implements Controller {
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Book> books = new ArrayList<>(bookService.getAll());
        ArrayList<BookVO> bookVOS = new ArrayList<>();
        for (Book book : books) {
            BookVO bookVO = bookService.getBookVO(book);
            bookVOS.add(bookVO);
        }
        req.getSession().setAttribute("Msg", "");
        req.getSession().setAttribute("bookVOS", bookVOS);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
