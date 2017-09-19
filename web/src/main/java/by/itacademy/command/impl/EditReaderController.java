package by.itacademy.command.impl;

import by.itacademy.Reader;
import by.itacademy.ReaderService;
import by.itacademy.impl.ReaderServiceImpl;
import by.itacademy.command.Controller;

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
public class EditReaderController implements Controller {
    private ReaderService readerService = ReaderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getMethod().equals("GET")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        } else {
            Reader sessionReader = (Reader) req.getSession().getAttribute("sreader");
            Reader newReader = new Reader();
            boolean validData = true;//flag to indicate whether all input data is valid
            if (req.getParameter("surname").matches("^[А-ЯЁ]([a-яё]){0,29}$") || req.getParameter("surname").length() == 0) {
                newReader.setSurname(req.getParameter("surname"));
            } else {
                validData = false;
            }
            if (req.getParameter("name").matches("^[А-ЯЁ]([a-яё]){0,29}$") || req.getParameter("name").length() == 0) {
                newReader.setName(req.getParameter("name"));
            } else {
                validData = false;
            }
            if (req.getParameter("secondname").matches("^[А-ЯЁ]([a-яё]){0,29}$") || req.getParameter("secondname").length() == 0) {
                newReader.setSecondName(req.getParameter("secondname"));
            } else {
                validData = false;
            }
            if (req.getParameter("em").matches("^([a-z0-9_\\.-]+\\@[\\da-z\\.-]+\\.[a-z\\.]{2,6})$") || req.getParameter("em").length() == 0) {
                Reader emreader = readerService.getByLogin(req.getParameter("em"));
                if (emreader != null && emreader.getReaderID() != sessionReader.getReaderID()) {
                    validData = false;
                }
                newReader.setEmail(req.getParameter("em"));
            } else {
                validData = false;
            }
            if (req.getParameter("pass").matches(".{6,30}") || req.getParameter("pass").length() == 0) {
                newReader.setPassword(req.getParameter("pass"));
            } else {
                validData = false;
            }
            LocalDate birthday;
            try {
                birthday = LocalDate.parse(req.getParameter("birthday"));
                if (birthday.compareTo(LocalDate.now().minus(18, ChronoUnit.YEARS)) < 0) {//check whether the author is over 18
                    newReader.setBirthday(birthday);
                } else {
                    validData = false;
                }
            } catch (DateTimeParseException e) {
                validData = false;
            }
            if (req.getParameter("gender").equals("1"))
                newReader.setGender("male");
            else if (req.getParameter("gender").equals("2"))
                newReader.setGender("female");
            else validData = false;

            if (validData) {
                readerService.update(sessionReader, newReader);
                req.getSession().setAttribute("errorMsg", "");
                req.getSession().setAttribute("sreader", readerService.get(sessionReader.getReaderID()));
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
}
