package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Reader;
import by.itacademy.service.ReaderService;
import by.itacademy.service.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project KR. Created by masiuk-l on 24.08.2017.
 */
public class BanReaderController implements Controller {
    private ReaderService readerService = ReaderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = readerService.get(Integer.parseInt(req.getParameter("readerID")));
        if (reader.getStatus().equals("BANNED")) {
            reader.setStatus("ACTIVE");
        } else {
            reader.setStatus("BANNED");
        }
        //System.out.println("Status of " + reader.getName() + ": " + reader.getStatus());
        readerService.update(reader);
        //Reader newReader = readerService.get(reader.getReaderID());
        //System.out.println("Status 2 " + newReader.getName() + ": " + newReader.getStatus());
    }
}
