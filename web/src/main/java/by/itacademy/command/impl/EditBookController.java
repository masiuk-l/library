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
 * Project KR. Created by masiuk-l on 21.08.2017.
 */
public class EditBookController implements Controller {
    private BookService bookService = BookServiceImpl.getInstance();
    private AuthorService authorService = AuthorServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getMethod().equals("GET")) {
            int bookID = Integer.parseInt(req.getParameter("id"));
            Book book = bookService.get(bookID);
            req.getSession().setAttribute("book", book);
            req.getSession().setAttribute("authors", authorService.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        } else {
            int bookID = Integer.parseInt(req.getParameter("id"));
            Book oldBook = bookService.get(bookID);
            Book newBook = new Book();
            boolean validData = true;//flag to indicate whether all input data is valid
            if (req.getParameter("name").matches("^.{1,29}$") || req.getParameter("name").length() == 0) {
                newBook.setName(req.getParameter("name"));
            } else {
                validData = false;
            }
            if (req.getParameter("isbn").matches("^[0-9\\\\-]{1,12}$") || req.getParameter("isbn").length() == 0) {
                newBook.setIsbn(req.getParameter("isbn"));
            } else {
                validData = false;
            }
            if (req.getParameter("genre").matches("^.{1,30}$") || req.getParameter("genre").length() == 0) {
                newBook.setGenre(req.getParameter("genre"));
            } else {
                validData = false;
            }
            int year;
            try {
                if (req.getParameter("year").length() == 0) {
                    newBook.setYear(0);
                } else {
                    year = Integer.parseInt(req.getParameter("year"));
                    if (year <= LocalDate.now().getYear() && year > 0) {
                        newBook.setYear(year);
                    } else {
                        validData = false;
                    }
                }

            } catch (NumberFormatException e) {
                validData = false;
            }
            int quantity;
            try {
                quantity = Integer.parseInt(req.getParameter("quantity"));
                if (quantity <= 999 && quantity > 0) {
                    newBook.setQuantity(quantity);
                } else {
                    validData = false;
                }
            } catch (NumberFormatException e) {
                validData = false;
            }

            if (validData) {

                newBook.getAuthors().clear();
                String[] authorIDs = req.getParameterValues("author");
                for (String authorID : authorIDs) {
                    newBook.getAuthors().add(authorService.get(authorID));
                }
                bookService.update(oldBook, newBook);
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
