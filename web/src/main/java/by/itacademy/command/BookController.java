package by.itacademy.command;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;
import by.itacademy.service.BookService;
import by.itacademy.service.FormService;
import by.itacademy.service.ReaderService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
@Log4j
@Controller
@RequestMapping("/book")
public class BookController {
    public static final String CATALOG = "catalog";
    public static final String MAIN_ERROR = "book/add";//todo ???
    public static final String MAIN_BOOK = "catalog/book";
    public static final String MAIN_CATALOG = "catalog";

    @Autowired
    private BookService bookService;
    @Autowired
    private FormService formService;
    @Autowired
    private ReaderService readerService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(@Valid Book book, BindingResult br, ModelMap model) {
        if (!br.hasErrors()) {
            if (book != null) {
                book = bookService.save(book);
            }
            return CATALOG;
        } else { //forward user to the same page with error message
            model.put("errorMsg", "Invalid data. Please, retry");
            return MAIN_ERROR;//todo редирект
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String book(ModelMap model, @PathVariable(value = "id") Integer id) {
        Book book = bookService.get(id);
        List<Reader> readers = bookService.getBookReaders(book);
        model.put("book", book);
        model.put("readers", readers);
        model.put("pageName", "book");
        log.error("book");
        return MAIN_BOOK;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String books(ModelMap model, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size) {
        ArrayList<Book> books = new ArrayList<>(bookService.getCatalogPage(page, size));
        model.put("books", books);
        int pageCount = (int) Math.ceil(bookService.getAll().size() / (double) size);
        model.put("pageCount", pageCount);
        model.put("pageNumber", page);
        model.put("pageName", "catalog");
        return MAIN_CATALOG;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable(value = "id") Integer id) {
        Book book = bookService.get(id);
        if (book != null) {
            bookService.delete(book.getBookID());
        }
        return MAIN_CATALOG;//todo redirect?
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(ModelMap model, @PathVariable(value = "id") Integer id, @Valid Book book, BindingResult br) {
        if (!br.hasErrors()) {
            if (book != null) {
                book = bookService.save(book);
            }
            return CATALOG;
        } else { //forward user to the same page with error message
            model.put("errorMsg", "Invalid data. Please, retry");
            return MAIN_ERROR;//todo правильно (это вообще 2 метода!)
        }
    }

    @RequestMapping(value = "/myBooks", method = RequestMethod.GET)
    public String myBooks(ModelMap model, HttpServletRequest request) {
        if (request.getSession().getAttribute("sreader") == null) {
            return CATALOG;
        }
        Reader reader = (Reader) request.getSession().getAttribute("sreader");
        ArrayList<Form> forms = new ArrayList<>(formService.getByReader(reader));
        model.put("forms", forms);
        return MAIN_CATALOG;
    }

    @RequestMapping(value = "/reserve/{id}", method = RequestMethod.GET)
    public void reserve(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) throws IOException {
        Book book = bookService.get(id);

        AtomicInteger quantity = new AtomicInteger(book.getQuantity());
        int currentCount = quantity.decrementAndGet();
        book.setQuantity(currentCount);
        bookService.update(book);
        Form form = new Form();
        form.setBook(book);
        form.setReader(((Reader) request.getSession().getAttribute("sreader")));
        form.setLibrarian(new Librarian());
        form.setReceivalType("Абонемент");
        form.setReceivalDate(LocalDate.now());
        form.setReturnDate(LocalDate.now().plus(14, ChronoUnit.DAYS));
        formService.save(form);
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(currentCount));
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET)
    public void returnBook(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) {
        Book book = bookService.get(id);
        Form form = null;
        if (request.getSession().getAttribute("sreader") == null) {
//            String contextPath = request.getContextPath();
//            resp.sendRedirect(contextPath + "/frontController?command=error");todo yf стр ошибки
        }
        int readerID = ((Reader) request.getSession().getAttribute("sreader")).getReaderID();
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
//            String contextPath = request.getContextPath();
//            response.sendRedirect(contextPath + "/frontController?command=error");//todo yf стр ошибки
        } else {
            formService.delete(form.getFormID());
        }
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(ModelMap model, @RequestParam(value = "name") String name, HttpServletRequest request) {
        model.put("pageName", "catalog");
        if (name.length() < 3 || name.length() > 30) {
            model.put("books", null);
            model.put("Msg", "Invalid input");

        } else {
            ArrayList<Book> books = new ArrayList<>(bookService.searchByName(name));
            if (books.isEmpty()) {
                model.put("books", null);
                model.put("pageCount", null);
                model.put("Msg", "No books match your input");
            } else {
                model.put("books", books);
                int pageCount = (int) Math.ceil(books.size() / (double) 3);//todo заменить на количество страниц в настройках
                model.put("pageCount", pageCount);
            }
        }
        return MAIN_CATALOG;//todo пока работает криво
    }
}
