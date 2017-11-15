package by.itacademy.service.impl;

import by.itacademy.entities.Book;
import by.itacademy.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class BookServiceImplTest {
    @Autowired
    BookService bookService;
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
    public void saveAndGetByName() throws Exception {
        book = bookService.save(book);
        Book newBook = bookService.searchByName("Книга").get(0);
        Assert.assertEquals(book.getName(), newBook.getName());
        bookService.delete(newBook.getBookID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        book = bookService.save(book);
        Book newBook = new Book(null, "Не книга", "", "", 0, 0, null, null);
        bookService.update(book, newBook);
        Book bookFromDb = bookService.get(book.getBookID());
        System.out.println(book);
        System.out.println(bookFromDb);
        System.out.println(book.equals(newBook));
        Assert.assertEquals("Не книга", bookFromDb.getName());
        bookService.delete(book.getBookID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        book = bookService.save(book);
        List<Book> books = bookService.getAll();
        int oldSize = books.size();
        bookService.delete(book.getBookID());
        books = bookService.getAll();
        Assert.assertEquals(oldSize - 1, books.size());
    }

    @Test
    public void getCatalogPage() throws Exception {
        Book book1 = new Book(null, "iji", "jkiji", "jij", 678, 7, null, null);
        Book book2 = new Book(null, "iji", "jkiji", "jij", 678, 7, null, null);
        Book book3 = new Book(null, "iji", "jkiji", "jij", 678, 7, null, null);
        Book book4 = new Book(null, "iji", "jkiji", "jij", 678, 7, null, null);
        Book book5 = new Book(null, "iji", "jkiji", "jij", 678, 7, null, null);
        Book book6 = new Book(null, "iji", "jkiji", "jij", 678, 7, null, null);
        book = bookService.save(book1);
        book = bookService.save(book2);
        book = bookService.save(book3);
        book = bookService.save(book4);
        book = bookService.save(book5);
        book = bookService.save(book6);
        bookService.getCatalogPage(1, 3).forEach(System.out::println);
        Assert.assertTrue(bookService.getCatalogPage(1, 3).get(0).getBookID() >= 1);


    }


}