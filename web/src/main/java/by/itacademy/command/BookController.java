package by.itacademy.command;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;
import by.itacademy.service.AuthorService;
import by.itacademy.service.BookService;
import by.itacademy.service.FormService;
import by.itacademy.service.LibrarianService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class BookController {//todo general return errorhandler
    public static final String CATALOG = "catalog";
    public static final String BOOK_ADD = "admin/addBook";
    public static final String BOOK_EDIT = "admin/editBook";
    public static final String MAIN_BOOK = "catalog/book";
    public static final String MY_BOOKS = "cabinet/myBooks";

    @Autowired
    private BookService bookService;
    @Autowired
    private FormService formService;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddBook(ModelMap model, HttpServletRequest req) {
        model.put("authors", authorService.getAll());
        model.put("pageName", "addbook");
        return BOOK_ADD;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(ModelMap model, HttpServletRequest req) {
        model.put("authors", authorService.getAll());
        Book book = new Book();
        boolean validData = true; //flag to indicate whether all input data is valid
        if (req.getParameter("name").matches("^.{1,29}$")) {
            book.setName(req.getParameter("name"));
        } else {
            validData = false;
        }
        if (req.getParameter("isbn").matches("^[0-9\\\\-]{1,12}$")) {
            book.setIsbn(req.getParameter("isbn"));
        } else {
            validData = false;
        }
        if (req.getParameter("genre").matches("^.{1,30}$")) {
            book.setGenre(req.getParameter("genre"));
        } else {
            validData = false;
        }
        int year;
        try {
            year = Integer.parseInt(req.getParameter("year"));
            if (year <= LocalDate.now().getYear() && year > 0) {
                book.setYear(year);
            } else {
                validData = false;
            }
        } catch (NumberFormatException e) {
            validData = false;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(req.getParameter("quantity"));
            if (quantity <= 999 && quantity > 0) {
                book.setQuantity(quantity);
            } else {
                validData = false;
            }
        } catch (NumberFormatException e) {
            validData = false;
        }

        if (validData) {
            String[] authorIDs = req.getParameterValues("author");
            for (String authorID : authorIDs) {
                book.getAuthors().add(authorService.get(Integer.parseInt(authorID)));
            }
            bookService.save(book);
        } else { //forward user to the same page with error message
            model.put("errorMsg", "Invalid data. Please, retry");

        }
        return "redirect:/book/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String book(ModelMap model, @PathVariable(value = "id") Integer id) {
        Book book = bookService.get(id);
        List<Reader> readers = bookService.getBookReaders(book);
        model.put("book", book);
        model.put("readers", readers);
        model.put("pageName", "book");
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
        return CATALOG;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable(value = "id") Integer id) {
        Book book = bookService.get(id);
        if (book != null) {
            bookService.delete(book.getBookID());
        }
        return "redirect:/book/";//todo redirect?
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditBook(ModelMap model, @PathVariable(value = "id") Integer id, HttpServletRequest req) {
        Book book = bookService.get(id);
        model.put("book", book);
        model.put("authors", authorService.getAll());
        model.put("pageName", "editBook");
        return BOOK_EDIT;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editBook(ModelMap model, @PathVariable(value = "id") Integer id, HttpServletRequest req) {
        Book oldBook = bookService.get(id);
        Book newBook = new Book();
        boolean validData = true;//flag to indicate whether all input data is valid
        if (req.getParameter("name").matches("^.{1,29}$") || req.getParameter("name").length() == 0) {
            newBook.setName(req.getParameter("name"));
        } else {
            validData = false;
        }
        if (req.getParameter("isbn").matches("^[0-9\\\\-]{1,12}$") || req.getParameter("isbn").length() == 0) {
            newBook.setIsbn(req.getParameter("isbn"));
        } else {
            validData = false;
        }
        if (req.getParameter("genre").matches("^.{1,30}$") || req.getParameter("genre").length() == 0) {
            newBook.setGenre(req.getParameter("genre"));
        } else {
            validData = false;
        }
        int year;
        try {
            if (req.getParameter("year").length() == 0) {
                newBook.setYear(0);
            } else {
                year = Integer.parseInt(req.getParameter("year"));
                if (year <= LocalDate.now().getYear() && year > 0) {
                    newBook.setYear(year);
                } else {
                    validData = false;
                }
            }

        } catch (NumberFormatException e) {
            validData = false;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(req.getParameter("quantity"));
            if (quantity <= 999 && quantity > 0) {
                newBook.setQuantity(quantity);
            } else {
                validData = false;
            }
        } catch (NumberFormatException e) {
            validData = false;
        }

        if (validData) {
            newBook.getAuthors().clear();
            String[] authorIDs = req.getParameterValues("author");
            for (String authorID : authorIDs) {
                newBook.getAuthors().add(authorService.get(Integer.parseInt(authorID)));
            }
            bookService.update(oldBook, newBook);

        } else { //forward user to the same page with error message
            model.put("errorMsg", "Invalid data. Please, retry");
        }
        return "redirect:/book/";
    }

    @RequestMapping(value = "/myBooks", method = RequestMethod.GET)
    public String myBooks(ModelMap model, HttpServletRequest request) {
        if (request.getSession().getAttribute("sreader") == null) {
            return "redirect:/book/";
        }
        Reader reader = (Reader) request.getSession().getAttribute("sreader");
        ArrayList<Form> forms = new ArrayList<>(formService.getByReader(reader));
        model.put("forms", forms);
        model.put("pageName", "mybooks");
        return MY_BOOKS;
    }

    @RequestMapping(value = "/reserve/{id}", method = RequestMethod.GET)
    public void reserve(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) throws IOException {
        Book book = bookService.get(id);
        AtomicInteger quantity = new AtomicInteger(book.getQuantity());
        int currentCount = quantity.decrementAndGet();
        book.setQuantity(currentCount);
        bookService.update(book);
        Form form = new Form(null, "Абонемент", LocalDate.now(), LocalDate.now().plus(14, ChronoUnit.DAYS), book, librarianService.get(1), (Reader) request.getSession().getAttribute("sreader"));
        formService.save(form);
        log.error("book");
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(currentCount));
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET)
    public void returnBook(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        Book book = bookService.get(id);
        Form form = new Form();
        if (request.getSession().getAttribute("sreader") != null) {
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
        return CATALOG;//todo пока работает криво
    }
}
