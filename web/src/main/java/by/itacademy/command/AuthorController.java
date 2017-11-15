package by.itacademy.command;

import by.itacademy.entities.Author;
import by.itacademy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Project KR. Created by masiuk-l on 22.08.2017.
 */
@Controller
@RequestMapping("/author")
public class AuthorController {
    public static final String BOOK_ADD = "book/add";
//    public static final String MAIN_ERROR = "author/add";

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAuthor(HttpServletRequest req, ModelMap model) {
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
        } else { //forward user to the same page with error message
            model.put("errorMsg", "Invalid data. Please, retry");
            model.put("pageName", "addbook");
            return BOOK_ADD;
        }
        return "redirect:/book/add";
    }
}
