package by.itacademy.dao;


import by.itacademy.entities.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface AuthorDAO extends CrudRepository<Author, Integer> {
}
