package by.itacademy.dao.impl;

import by.itacademy.dao.BookDAO;
import by.itacademy.entities.Book;
import by.itacademy.util.HibernateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */
public class BookDAOImplTest {
    private BookDAO bookDAO;
    private Book book;

    @Before
    public void createBook() {
        bookDAO = BookDAOImpl.getInstance();
        book = new Book();
        book.setName("Книга");
        book.setIsbn("03293849310");
        book.setGenre("Роман");
        book.setYear(1996);
        book.setQuantity(42);
        HibernateUtil.getEntityManager("by.itacademy.test");
        HibernateUtil.beginTransaction();
    }

    @Test
    public void saveAndGetByName() throws Exception {
        bookDAO.save(book);
//        Book newBook = bookDAO.getByName("Книга").get(0);
//        Assert.assertEquals(book.getName(), newBook.getName());
//        bookDAO.delete(newBook.getBookID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        bookDAO.save(book);
//        book = bookDAO.getByName("Книга").get(0);
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

    @After
    public void tearDown() {
        HibernateUtil.commit();
        HibernateUtil.closeEntityManager();
    }
}