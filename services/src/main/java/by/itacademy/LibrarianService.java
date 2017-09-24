package by.itacademy;

import by.itacademy.entities.Librarian;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface LibrarianService extends Service<Librarian> {

    /**
     * @param surname surname
     * @return List of librarians matching the input
     */
    List<Librarian> getBySurname(String surname);

    /**
     * @param login login
     * @return Librarian matching the input
     */
    Librarian getByLogin(String login);


}
