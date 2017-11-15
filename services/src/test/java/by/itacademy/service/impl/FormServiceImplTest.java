package by.itacademy.service.impl;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;
import by.itacademy.service.BookService;
import by.itacademy.service.FormService;
import by.itacademy.service.LibrarianService;
import by.itacademy.service.ReaderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class FormServiceImplTest {
    @Autowired
    FormService formService;

    @Autowired
    LibrarianService librarianService;

    @Autowired
    ReaderService readerService;

    @Autowired
    private BookService bookService;


    private Form form;
    private Book book;

    @Before
    public void createForm() {
        form = new Form();
        book = new Book();
        book.setName("Книга");
        book.setIsbn("03293849310");
        book.setGenre("Роман");
        book.setYear(1996);
        book.setQuantity(42);
        form.setBook(book);
        Librarian librarian = new Librarian();
        librarian.setName("Иван");
        librarian.setSecondName("Иванович");
        librarian.setSurname("Иванов");
        librarian.setEmail("ffr@www");
        librarian.setPassword("fvfdcsdv");
        form.setLibrarian(librarian);
        Reader reader = new Reader();
        reader.setName("Иван");
        reader.setSecondName("Иванович");
        reader.setSurname("Козлов");
        reader.setBirthday(LocalDate.of(1996, 12, 1));
        reader.setEmail("ffr@www");
        reader.setPassword("fvfdcsdv");
        reader.setGender("женский");
        reader.setStatus("");
        form.setReader(reader);
        form.setReceivalType("Формуляр");
        form.setReceivalDate(LocalDate.now());
        form.setReturnDate(LocalDate.now().plus(14, ChronoUnit.DAYS));
        book = bookService.save(book);
        librarian = librarianService.save(librarian);
        reader = readerService.save(reader);
        form = formService.save(form);
    }

    @Test
    public void saveAndGetByReceivalType() throws Exception {
        Form newForm = formService.getByBook(book).get(0);
        Assert.assertEquals(form.toString(), newForm.toString());
        formService.delete(newForm.getFormID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        form.setReceivalType("Формулярpp");
        formService.update(form);
        Form newForm = formService.get(form.getFormID());
        Assert.assertEquals(form.toString(), newForm.toString());
        formService.delete(form.getFormID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        List<Form> forms = formService.getAll();
        int oldSize = forms.size();
        formService.delete(form.getFormID());
        forms = formService.getAll();
        Assert.assertEquals(oldSize - 1, forms.size());
    }

}