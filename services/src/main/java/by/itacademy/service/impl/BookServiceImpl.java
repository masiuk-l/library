package by.itacademy.service.impl;

import by.itacademy.dao.BookDAO;
import by.itacademy.dao.DAO;
import by.itacademy.entities.Book;
import by.itacademy.entities.Reader;
import by.itacademy.service.BookService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of BookService
 */
@Service
@Transactional
public class BookServiceImpl extends IServiceImpl<Book> implements BookService {

    @Autowired
    BookDAO bookDAO;

    public BookServiceImpl(@Autowired DAO<Book, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void update(Book oldBook, Book newBook) {
        Book book = new Book();
        book.setBookID(oldBook.getBookID());
        book.setName((newBook.getName().length() == 0) ? oldBook.getName() : newBook.getName());
        book.setIsbn((newBook.getIsbn().length() == 0) ? oldBook.getIsbn() : newBook.getIsbn());
        book.setGenre((newBook.getGenre().length() == 0) ? oldBook.getGenre() : newBook.getGenre());
        book.setYear((newBook.getYear() == 0) ? oldBook.getYear() : newBook.getYear());
        book.setAuthors(newBook.getAuthors());
        book.setQuantity(newBook.getQuantity());
        update(book);
    }


    @Override
    public List<Book> searchByName(String name) {
        String searchKey = name.toLowerCase();
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> allBooks = Lists.newArrayList(bookDAO.findAll());
        for (Book aBook : allBooks) {
            if (aBook.getName().toLowerCase().contains(searchKey) || aBook.getGenre().toLowerCase().contains(searchKey))
                books.add(aBook);
        }
        return books;
    }

    @Override
    public List<Book> getCatalogPage(int pageNumber, int size) {
        ArrayList<Book> books;
        books = Lists.newArrayList(bookDAO.findAll(new PageRequest(pageNumber - 1, size)));
        return books;
    }

    @Override
    public List<Reader> getBookReaders(Book book) {
        return Lists.newArrayList(bookDAO.getBookReaders(book));
    }
}
