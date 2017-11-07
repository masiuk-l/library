package by.itacademy.dao;

import by.itacademy.entities.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookDAO extends DAO<Book> {
    List<Book> getCatalogPage(int pageNumber, int size) throws SQLException;

}
