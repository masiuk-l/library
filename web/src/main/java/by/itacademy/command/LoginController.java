package by.itacademy.command;

import by.itacademy.dao.auth.Encoder;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;
import by.itacademy.service.LibrarianService;
import by.itacademy.service.ReaderService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Project KR. Created by masiuk-l on 18.08.2017.
 */
@Log4j
@Controller
@RequestMapping("/login")
public class LoginController {
    public static final String MAIN = "main";
    public static final String LOGIN = "login";

    @Autowired
    ReaderService readerService;

    @Autowired
    LibrarianService librarianService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest req) {
        log.error("login");
        model.put("pageName", "login");
        return LOGIN;
    }

    @RequestMapping(value = "/reader", method = RequestMethod.POST)
    public String loginReader(ModelMap model, HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null) {
            return LOGIN;
        }
        Reader reader = readerService.getByLogin(login);
        if (reader != null && reader.getPassword().equals(Encoder.encode(password))) {
            //if (reader != null && password.equals(reader.getPassword())) {
            req.getSession().setAttribute("sreader", reader);
            return MAIN;
        } else {
            model.put("errorMsg", "Invalid Login or Password");
            return LOGIN;
        }
    }

    @RequestMapping(value = "/lib", method = RequestMethod.POST)
    public String loginLib(ModelMap model, HttpServletRequest req) {
        String login = req.getParameter("loginlib");
        String password = req.getParameter("passwordlib");
        if (login == null || password == null) {
            return LOGIN;
        }
        Librarian librarian = librarianService.getByLogin(login);
        if (librarian != null && librarian.getPassword().equals(Encoder.encode(password))) {
            //if (librarian != null && password.equals(librarian.getPassword())) {
            req.getSession().setAttribute("slibrarian", librarian);

            return MAIN;
        } else {
            model.put("errorMsg", "Invalid Login or Password");
            return LOGIN;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest req) {
        req.getSession().invalidate();
        return MAIN;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String singUp(ModelMap model, HttpServletRequest req) {
//
//
//        boolean validData = true;//flag to indicate whether all input data is valid
//        Reader reader = new Reader();
//        if (req.getParameter("surname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
//            reader.setSurname(req.getParameter("surname"));
//        } else {
//            validData = false;
//        }
//        if (req.getParameter("name").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
//            reader.setName(req.getParameter("name"));
//        } else {
//            validData = false;
//        }
//        if (req.getParameter("secondname").matches("^[А-ЯЁ]([a-яё]){0,29}$")) {
//            reader.setSecondName(req.getParameter("secondname"));
//        } else {
//            validData = false;
//        }
//        if (req.getParameter("email").matches("^([a-z0-9_\\.-]+\\@[\\da-z\\.-]+\\.[a-z\\.]{2,6})$")) {
//            Reader emreader = readerService.getByLogin(req.getParameter("email"));
//            if (emreader != null) {
//                validData = false;
//            }
//            reader.setEmail(req.getParameter("email"));
//        } else {
//            validData = false;
//        }
//        if (req.getParameter("password").matches(".{6,30}")) {
//            reader.setPassword(req.getParameter("password"));
//        } else {
//            validData = false;
//        }
//        LocalDate birthday;
//        try {
//            birthday = LocalDate.parse(req.getParameter("birthday"));
//            if (birthday.compareTo(LocalDate.now().minus(18, ChronoUnit.YEARS)) < 0) {//check whether the author is over 18
//                reader.setBirthday(birthday);
//            } else {
//                validData = false;
//            }
//        } catch (DateTimeParseException e) {
//            validData = false;
//        }
//        if (req.getParameter("gender").equals("1"))
//            reader.setGender("male");
//        else if (req.getParameter("gender").equals("2"))
//            reader.setGender("female");
//        else validData = false;
//        reader.setStatus("ACTIVE");
//
//        if (validData) {
//            readerService.save(reader);
//            req.getSession().setAttribute("errorMsg", "");
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=main");
//            return;
//        } else { //forward user to the same page with error message
//            req.getSession().setAttribute("errorMsg", "Invalid data. Please, retry");
//            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
//            dispatcher.forward(req, resp);
//            return;
//        }
//
        return MAIN;
    }

}
