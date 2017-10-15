package by.itacademy.dao.impl;

import by.itacademy.dao.FormDAO;
import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

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
        log.info("Get forms by book:" + book);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Form where BOOK_ID = :bookID");
        query.setParameter("bookID", book.getBookID());
        return query.getResultList();
    }

    @Override
    public List<Form> getByReader(Reader reader) throws SQLException {
        log.info("Get forms by reader:" + reader);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Form where READER_ID = :readerID");
        query.setParameter("readerID", reader.getReaderID());
        return query.getResultList();
    }

    @Override
    public List<Form> getByLibrarian(Librarian librarian) throws SQLException {
        log.info("Get forms by librarian:" + librarian);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Form where LIBRARIAN_ID = :librarianID");
        query.setParameter("librarianID", librarian.getLibrarianID());
        return query.getResultList();
    }

    @Override
    public List<Form> getByReceivalType(String receivalType) throws SQLException {
        log.info("Get forms by receivalType:" + receivalType);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Form where RECEIVAL_TYPE = :receivalType");
        query.setParameter("receivalType", receivalType);
        return query.getResultList();
    }

}
