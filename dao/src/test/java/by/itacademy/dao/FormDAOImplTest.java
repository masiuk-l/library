package by.itacademy.dao;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-context.xml")
@Transactional(transactionManager = "transactionManager")
public class FormDAOImplTest {
    @Autowired
    FormDAO formDAO;
    @Autowired
    BookDAO bookDAO;
    private Form form;
    private Book book;
    private Reader reader;
    private Librarian librarian;

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
        librarian = new Librarian();
        librarian.setName("Иван");
        librarian.setSecondName("Иванович");
        librarian.setSurname("Иванов");
        librarian.setEmail("ffr@ww");
        librarian.setPassword("fvfdcsdv");
        form.setLibrarian(librarian);
        reader = new Reader();
        reader.setName("Иван");
        reader.setSecondName("Иванович");
        reader.setSurname("Козлов");
        reader.setBirthday(LocalDate.of(1996, 12, 1));
        reader.setEmail("ffr@ww");
        reader.setPassword("fvfdcsdv");
        reader.setGender("женский");
        reader.setStatus("");
        form.setReader(reader);
        form.setReceivalType("Формуляр");
        form.setReceivalDate(LocalDate.now());
        form.setReturnDate(LocalDate.now().plus(14, ChronoUnit.DAYS));
    }

    @Test
    public void saveAndGetByReceivalType() throws Exception {
        form = formDAO.save(form);
        Form newForm = formDAO.findAll().iterator().next();
        Assert.assertEquals(form.toString(), newForm.toString());
        formDAO.delete(newForm.getFormID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        form = formDAO.save(form);
        form.setReceivalType("Формулярpp");
        formDAO.save(form);
        Form newForm = formDAO.findAll().iterator().next();
        Assert.assertEquals(form.toString(), newForm.toString());
        formDAO.delete(form.getFormID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        formDAO.save(form);
        List<Form> forms = new ArrayList<>();
        formDAO.findAll().forEach(forms::add);
        int oldSize = forms.size();
        formDAO.delete(form.getFormID());
        forms.clear();
        formDAO.findAll().forEach(forms::add);
        Assert.assertEquals(oldSize - 1, forms.size());
    }

    @Test
    public void hnf() {
        form = formDAO.save(form);
        System.out.println(bookDAO.getBookReaders(book));// todo причесать
    }

}