package by.itacademy.command.impl;

import by.itacademy.AuthorService;
import by.itacademy.command.Controller;
import by.itacademy.entities.Author;
import by.itacademy.impl.AuthorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Project KR. Created by masiuk-l on 22.08.2017.
 */
public class AddAuthorController implements Controller {
    private AuthorService authorService = AuthorServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Author author = new Author();
        boolean validData = true;//flag to indicate whether all input data is valid
        if (req.getParameter("surname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            author.setSurname(req.getParameter("surname"));
        } else {
            validData = false;
        }
        if (req.getParameter("surname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            author.setSurname(req.getParameter("surname"));
        } else {
            validData = false;
        }
        if (req.getParameter("name").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            author.setName(req.getParameter("name"));
        } else {
            validData = false;
        }
        if (req.getParameter("secondname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            author.setSecondName(req.getParameter("secondname"));
        } else {
            validData = false;
        }
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(req.getParameter("birthday"));
            if (birthday.compareTo(LocalDate.now().minus(18, ChronoUnit.YEARS)) < 0) {//check whether the author is over 18
                author.setBirthday(birthday);
            } else {
                validData = false;
            }
        } catch (DateTimeParseException e) {
            validData = false;
        }

        if (req.getParameter("country").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            author.setCountry(req.getParameter("country"));
        } else {
            validData = false;
        }

        if (validData) {
            authorService.save(author);
            req.getSession().setAttribute("errorMsg", "");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=addBook");
            return;
        } else { //forward user to the same page with error message
            req.getSession().setAttribute("errorMsg", "Invalid data. Please, retry");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }

    }
}
