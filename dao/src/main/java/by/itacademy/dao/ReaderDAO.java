package by.itacademy.dao;

import by.itacademy.entities.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface ReaderDAO extends CrudRepository<Reader, Integer>, DAO<Reader> {

    /**
     * @param email email
     * @return List of readers matching the input
     */
    List<Reader> findByEmail(String email);

}
