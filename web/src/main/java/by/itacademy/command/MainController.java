package by.itacademy.command;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
@Log4j
@Controller
@RequestMapping("/main")
public class MainController {
    public static final String MAIN = "main";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap model) {
        log.error("main");
        model.put("pageName", "main");
        return MAIN;
    }
}
