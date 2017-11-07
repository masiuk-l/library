package by.itacademy;

import by.itacademy.entities.Librarian;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface LibrarianService extends Service<Librarian> {


    /**
     * @param login login
     * @return Librarian matching the input
     */
    Librarian getByLogin(String login);


}
