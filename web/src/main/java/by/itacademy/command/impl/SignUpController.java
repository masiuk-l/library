package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Reader;
import by.itacademy.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class SignUpController implements Controller {
    @Autowired
    private ReaderService readerService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        boolean validData = true;//flag to indicate whether all input data is valid
        Reader reader = new Reader();
        if (req.getParameter("surname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            reader.setSurname(req.getParameter("surname"));
        } else {
            validData = false;
        }
        if (req.getParameter("name").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            reader.setName(req.getParameter("name"));
        } else {
            validData = false;
        }
        if (req.getParameter("secondname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
            reader.setSecondName(req.getParameter("secondname"));
        } else {
            validData = false;
        }
        if (req.getParameter("email").matches("^([a-z0-9_\\.-]+\\@[\\da-z\\.-]+\\.[a-z\\.]{2,6})$")) {
            Reader emreader = readerService.getByLogin(req.getParameter("email"));
            if (emreader != null) {
                validData = false;
            }
            reader.setEmail(req.getParameter("email"));
        } else {
            validData = false;
        }
        if (req.getParameter("password").matches(".{6,30}")) {
            reader.setPassword(req.getParameter("password"));
        } else {
            validData = false;
        }
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(req.getParameter("birthday"));
            if (birthday.compareTo(LocalDate.now().minus(18, ChronoUnit.YEARS)) < 0) {//check whether the author is over 18
                reader.setBirthday(birthday);
            } else {
                validData = false;
            }
        } catch (DateTimeParseException e) {
            validData = false;
        }
        if (req.getParameter("gender").equals("1"))
            reader.setGender("male");
        else if (req.getParameter("gender").equals("2"))
            reader.setGender("female");
        else validData = false;
        reader.setStatus("ACTIVE");

        if (validData) {
            readerService.save(reader);
            req.getSession().setAttribute("errorMsg", "");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=main");
            return;
        } else { //forward user to the same page with error message
            req.getSession().setAttribute("errorMsg", "Invalid data. Please, retry");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }

    }
}
