package by.itacademy.command;

import by.itacademy.entities.Author;
import by.itacademy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Project KR. Created by masiuk-l on 22.08.2017.
 */
@Controller
@RequestMapping("/author")
public class AuthorController {
    public static final String MAIN = "book/add";
    public static final String MAIN_ERROR = "author/add";


    @Autowired
    AuthorService authorService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAuthor(@Valid Author author, BindingResult br, ModelMap model) {
        if (!br.hasErrors()) {
            if (author != null) {
                author = authorService.save(author);
            }
            return MAIN;
        } else { //forward user to the same page with error message
            model.put("errorMsg", "Invalid data. Please, retry");
            return MAIN_ERROR;
        }

    }
}
