package by.itacademy.command;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
@Controller
@RequestMapping("/about")
public class AboutController {
    public static final String MAIN = "about";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String about(ModelMap model) {
        model.put("pageName", "about");
        return MAIN;
    }
}
