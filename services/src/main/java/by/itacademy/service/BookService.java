package by.itacademy.service;

import by.itacademy.entities.Book;
import by.itacademy.entities.Reader;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface BookService extends IService<Book> {

    /**
     * @param oldBook initial book
     * @param newBook updated info
     */
    void update(Book oldBook, Book newBook);

    /**
     * @param name book name or genre (case insensitive)
     * @return List of books matching the input
     */
    List<Book> searchByName(String name);

    /**
     * @param pageNumber number of the catalog page
     * @param size       number of elements per page
     * @return List of books matching the input
     */
    List<Book> getCatalogPage(int pageNumber, int size);

    /**
     * @param book current book
     */
    List<Reader> getBookReaders(Book book);

}
