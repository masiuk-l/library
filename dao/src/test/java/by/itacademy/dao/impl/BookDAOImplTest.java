package by.itacademy.dao.impl;

import by.itacademy.dao.BookDAO;
import by.itacademy.entities.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional(transactionManager = "txManager")
public class BookDAOImplTest {
    @Autowired
    BookDAO bookDAO;
    private Book book;

    @Before
    public void createBook() {
        book = new Book();
        book.setName("Книга");
        book.setIsbn("03293849310");
        book.setGenre("Роман");
        book.setYear(1996);
        book.setQuantity(42);
    }

    @Test
    public void saveAndGet() throws Exception {
        book = bookDAO.save(book);
        Book newBook = bookDAO.getAll().get(0);
        Assert.assertEquals(book.getName(), newBook.getName());
        bookDAO.delete(newBook.getBookID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        bookDAO.save(book);
        book = bookDAO.getCatalogPage(1, 3).get(0);
        book.setName("Не книга");
        bookDAO.update(book);
        Book newBook = bookDAO.get(book.getBookID());
        Assert.assertTrue(book.equals(newBook));
        bookDAO.delete(book.getBookID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        bookDAO.save(book);
        List<Book> books = bookDAO.getAll();
        int oldSize = books.size();
        bookDAO.delete(book.getBookID());
        books = bookDAO.getAll();
        Assert.assertEquals(oldSize - 1, books.size());
    }

}