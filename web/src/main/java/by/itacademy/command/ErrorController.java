package by.itacademy.command;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
@Log4j
@Controller
@RequestMapping("error")
public class ErrorController {
    public static final String ERROR = "error";
    public static final String ERROR_404 = "error/404";

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound(ModelMap model) {
        log.error("404");
        model.put("pageName", "404");
        return ERROR_404;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String error(ModelMap model) {
        model.put("pageName", "error");
        return ERROR;
    }
}
