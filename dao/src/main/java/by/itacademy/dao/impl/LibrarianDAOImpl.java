package by.itacademy.dao.impl;

import by.itacademy.dao.LibrarianDAO;
import by.itacademy.entities.Librarian;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of LibrarianDAO interface
 */
@Log4j
public class LibrarianDAOImpl extends BaseDAOImpl<Librarian> implements LibrarianDAO {

    private static volatile LibrarianDAO INSTANCE = null;

    private LibrarianDAOImpl() {
    }

    public static LibrarianDAO getInstance() {
        LibrarianDAO librarianDAO = INSTANCE;
        if (librarianDAO == null) {
            synchronized (LibrarianDAOImpl.class) {
                librarianDAO = INSTANCE;
                if (librarianDAO == null) {
                    INSTANCE = librarianDAO = new LibrarianDAOImpl();
                }
            }
        }
        return librarianDAO;
    }

    @Override
    public List<Librarian> getByLogin(String login) throws SQLException {
        log.info("Get librarians by login:" + login);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Librarian where EMAIL = :login");
        query.setParameter("login", login);
        return query.getResultList();
    }

}
