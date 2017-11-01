package by.itacademy.dao;

import by.itacademy.entities.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookDAO extends DAO<Book> {

    /**
     * @param name
     * @return List of books matching the input
     * @throws SQLException
     */
    List<Book> getByName(String name) throws SQLException;

    /**
     * @param isbn isbn
     * @return List of books matching the input
     * @throws SQLException
     */
    List<Book> getByIsbn(String isbn) throws SQLException;

    /**
     * @param genre genre
     * @return List of books matching the input
     * @throws SQLException
     */
    List<Book> getByGenre(String genre) throws SQLException;

    List<Book> getCatalogPage(int pageNumber, int size) throws SQLException;

}
