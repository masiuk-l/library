package by.itacademy.dao;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface FormDAO extends DAO<Form> {
    /**
     * @param reader reader
     * @return List of forms matching the input
     * @throws SQLException
     */
    List<Form> getByReader(Reader reader) throws SQLException;

    /**
     * @param book book
     * @return List of forms matching the input
     * @throws SQLException
     */
    List<Form> getByBook(Book book) throws SQLException;


}
