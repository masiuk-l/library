package by.itacademy.dao;

import by.itacademy.entities.Reader;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface ReaderDAO extends DAO<Reader> {

    /**
     * @param login login
     * @return List of readers matching the input
     * @throws SQLException
     */
    List<Reader> getByLogin(String login) throws SQLException;

}
