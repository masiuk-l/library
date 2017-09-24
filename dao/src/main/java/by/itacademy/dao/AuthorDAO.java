package by.itacademy.dao;


import by.itacademy.entities.Author;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface AuthorDAO extends DAO<Author> {

    /**
     * @param surname surname
     * @return List of authors matching the input
     * @throws SQLException
     */
    List<Author> getBySurname(String surname) throws SQLException;

}
