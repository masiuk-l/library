package by.itacademy.dao.impl;

import by.itacademy.dao.BookDAO;
import by.itacademy.entities.Book;
import by.itacademy.util.SFUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of BookDAO interface
 */

@Log4j
public class BookDAOImpl extends BaseDAOImpl<Book> implements BookDAO {

    private static volatile BookDAO INSTANCE = null;

    private BookDAOImpl() {
    }

    public static BookDAO getInstance() {
        BookDAO bookDAO = INSTANCE;
        if (bookDAO == null) {
            synchronized (BookDAOImpl.class) {
                bookDAO = INSTANCE;
                if (bookDAO == null) {
                    INSTANCE = bookDAO = new BookDAOImpl();
                }
            }
        }
        return bookDAO;
    }

    public Session getSession() {
        return SFUtil.getSession();
    }

    @Override
    public List<Book> getByName(String name) throws SQLException {
        log.info("Get books by name:" + name);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from BOOK were NAME = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Book> getByIsbn(String isbn) throws SQLException {
        log.info("Get books by isbn:" + isbn);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from BOOK were ISBN = :isbn");
        query.setParameter("isbn", isbn);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenre(String genre) throws SQLException {
        log.info("Get books by genre:" + genre);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from BOOK were genre = :genre");
        query.setParameter("genre", genre);
        return query.getResultList();
    }


}

