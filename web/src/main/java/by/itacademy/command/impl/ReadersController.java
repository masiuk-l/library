package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Reader;
import by.itacademy.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 19.08.2017.
 */
public class ReadersController implements Controller {
    @Autowired
    private ReaderService readerService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Reader> readers = new ArrayList<>(readerService.getAll());
        req.getSession().setAttribute("readers", readers);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
