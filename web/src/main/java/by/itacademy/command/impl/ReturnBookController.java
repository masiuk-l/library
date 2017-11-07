package by.itacademy.command.impl;

import by.itacademy.command.Controller;
import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;
import by.itacademy.service.BookService;
import by.itacademy.service.FormService;
import by.itacademy.service.impl.BookServiceImpl;
import by.itacademy.service.impl.FormServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project KR. Created by masiuk-l on 16.08.2017.
 */
public class ReturnBookController implements Controller {
    private BookService bookService = BookServiceImpl.getInstance();
    private FormService formService = FormServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = bookService.get(Integer.parseInt(req.getParameter("bookID")));
        Form form = null;
        if (req.getSession().getAttribute("sreader") == null) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=error");
        }
        int readerID = ((Reader) req.getSession().getAttribute("sreader")).getReaderID();
        AtomicInteger quantity = new AtomicInteger(book.getQuantity());
        int currentCount = quantity.incrementAndGet();
        book.setQuantity(currentCount);
        bookService.update(book);
        ArrayList<Form> forms = new ArrayList<>(formService.getByBook(book));
        for (Form f : forms) {
            if (f.getReader().getReaderID() == readerID)
                form = f;
        }
        if (form == null) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=error");
        } else {
            formService.delete(form.getFormID());
        }
    }
}
