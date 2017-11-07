package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Book;
import by.itacademy.service.AuthorService;
import by.itacademy.service.BookService;
import by.itacademy.service.impl.AuthorServiceImpl;
import by.itacademy.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class AddBookController implements Controller {
    private AuthorService authorService = AuthorServiceImpl.getInstance();
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getSession().setAttribute("authors", authorService.getAll());
        if (req.getMethod().equals("GET")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } else {
            Book book = new Book();
            boolean validData = true; //flag to indicate whether all input data is valid
            if (req.getParameter("name").matches("^.{1,29}$")) {
                book.setName(req.getParameter("name"));
            } else {
                validData = false;
            }
            if (req.getParameter("isbn").matches("^[0-9\\\\-]{1,12}$")) {
                book.setIsbn(req.getParameter("isbn"));
            } else {
                validData = false;
            }
            if (req.getParameter("genre").matches("^.{1,30}$")) {
                book.setGenre(req.getParameter("genre"));
            } else {
                validData = false;
            }
            int year;
            try {
                year = Integer.parseInt(req.getParameter("year"));
                if (year <= LocalDate.now().getYear() && year > 0) {
                    book.setYear(year);
                } else {
                    validData = false;
                }
            } catch (NumberFormatException e) {
                validData = false;
            }
            int quantity;
            try {
                quantity = Integer.parseInt(req.getParameter("quantity"));
                if (quantity <= 999 && quantity > 0) {
                    book.setQuantity(quantity);
                } else {
                    validData = false;
                }
            } catch (NumberFormatException e) {
                validData = false;
            }

            if (validData) {
                String[] authorIDs = req.getParameterValues("author");
                for (String authorID : authorIDs) {
                    book.getAuthors().add(authorService.get(authorID));
                }
                bookService.save(book);
                req.getSession().setAttribute("errorMsg", "");
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/frontController?command=catalog");
                return;
            } else { //forward user to the same page with error message
                req.getSession().setAttribute("errorMsg", "Invalid data. Please, retry");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            }
        }
    }
}
