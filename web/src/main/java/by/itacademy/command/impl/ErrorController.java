package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.service.ReaderService;
import by.itacademy.service.impl.ReaderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class ErrorController implements Controller {
    ReaderService readerService = ReaderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        req.setAttribute("title", "error");
        dispatcher.forward(req, resp);
        return;
    }
}
