package by.itacademy.service;

import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface FormService extends IService<Form> {


    /**
     * @param reader reader
     * @return List of forms matching the input
     */
    List<Form> getByReader(Reader reader);


    /**
     * @param book book
     * @return List of forms matching the input
     */
    List<Form> getByBook(Book book);


}
