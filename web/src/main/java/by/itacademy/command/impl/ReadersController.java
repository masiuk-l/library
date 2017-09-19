package by.itacademy.command.impl;

import by.itacademy.ReaderVO;
import by.itacademy.Reader;
import by.itacademy.ReaderService;
import by.itacademy.impl.ReaderServiceImpl;
import by.itacademy.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 19.08.2017.
 */
public class ReadersController implements Controller {
    private ReaderService readerService = ReaderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Reader> readers = new ArrayList<>(readerService.getAll());
        ArrayList<ReaderVO> readerVOS = new ArrayList<>();
        for (Reader reader : readers) {
            ReaderVO readerVO = readerService.getReaderVO(reader);
            readerVOS.add(readerVO);
        }

        req.getSession().setAttribute("readerVOS", readerVOS);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
