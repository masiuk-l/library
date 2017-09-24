package by.itacademy;

import by.itacademy.entities.Author;

import java.util.List;


/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface AuthorService extends Service<Author> {

    /**
     * @param surname surname
     * @return List of authors matching the input
     */
    List<Author> getBySurname(String surname);

}
