package by.itacademy;

import by.itacademy.entities.Book;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface BookService extends Service<Book> {

    /**
     * @param name
     * @return List of books matching the input
     */
    List<Book> getByName(String name);

    /**
     * @param isbn isbn
     * @return List of books matching the input
     */
    List<Book> getByIsbn(String isbn);

    /**
     * @param genre genre
     * @return List of books matching the input
     */
    List<Book> getByGenre(String genre);

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
     * @param book book
     * @return Value object of the book
     */
//    BookVO getBookVO(Book book);
}
