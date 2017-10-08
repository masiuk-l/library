package by.itacademy.dao.impl;

import by.itacademy.dao.FormDAO;
import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;
import lombok.extern.log4j.Log4j;

import java.sql.SQLException;
import java.util.List;


/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 *
 * Implementation of FormAuthorDAO interface
 */

@Log4j
public class FormDAOImpl extends BaseDAOImpl<Form> implements FormDAO {

    private static volatile FormDAO INSTANCE = null;

    private FormDAOImpl() {
    }

    public static FormDAO getInstance() {
        FormDAO formDAO = INSTANCE;
        if (formDAO == null) {
            synchronized (FormDAOImpl.class) {
                formDAO = INSTANCE;
                if (formDAO == null) {
                    INSTANCE = formDAO = new FormDAOImpl();
                }
            }
        }

        return formDAO;
    }


    @Override
    public List<Form> getByBook(Book book) throws SQLException {

    }

    @Override
    public List<Form> getByReader(Reader reader) throws SQLException {

    }

    @Override
    public List<Form> getByLibrarian(Librarian librarian) throws SQLException {

    }

    @Override
    public List<Form> getByReceivalType(String receivalType) throws SQLException {

    }

}
