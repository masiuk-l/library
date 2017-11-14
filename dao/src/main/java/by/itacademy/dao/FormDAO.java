package by.itacademy.dao;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface FormDAO extends DAO<Form, Integer> {
    /**
     * @param reader reader
     * @return List of forms matching the input
     */
    List<Form> findByReader(Reader reader);

    /**
     * @param book book
     * @return List of forms matching the input
     */
    List<Form> findByBook(Book book);


}
