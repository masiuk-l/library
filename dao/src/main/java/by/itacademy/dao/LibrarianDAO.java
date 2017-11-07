package by.itacademy.dao;

import by.itacademy.entities.Librarian;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface LibrarianDAO extends CrudRepository<Librarian, Integer> {

    /**
     * @param email email
     * @return List of librarians matching the input
     */
    List<Librarian> findByEmail(String email);
}
