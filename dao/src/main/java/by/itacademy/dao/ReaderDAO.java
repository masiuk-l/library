package by.itacademy.dao;

import by.itacademy.entities.Reader;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface ReaderDAO extends DAO<Reader> {

    /**
     * @param login login
     * @return List of readers matching the input
     */
    List<Reader> getByLogin(String login);

}
