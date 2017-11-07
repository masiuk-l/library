package by.itacademy.dao;

import by.itacademy.entities.Librarian;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface LibrarianDAO extends DAO<Librarian> {

    /**
     * @param login login
     * @return List of librarians matching the input
     */
    List<Librarian> getByLogin(String login);
}
