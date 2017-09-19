package by.itacademy;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookAuthorDAO extends DAO<BookAuthor> {

    /**
     * @param author author
     * @return List of bookAuthors matching the input
     * @throws SQLException
     */
    List<BookAuthor> getByAuthorID(Author author) throws SQLException;

    /**
     * @param book book
     * @return List of bookAuthors matching the input
     * @throws SQLException
     */
    List<BookAuthor> getByBookID(Book book) throws SQLException;

}
