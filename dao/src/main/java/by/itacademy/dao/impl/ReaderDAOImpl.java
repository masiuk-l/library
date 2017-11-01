package by.itacademy.dao.impl;

import by.itacademy.dao.ReaderDAO;
import by.itacademy.entities.Reader;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of ReaderDAO interface
 */

@Log4j
public class ReaderDAOImpl extends BaseDAOImpl<Reader> implements ReaderDAO {

    private static volatile ReaderDAO INSTANCE = null;

    private ReaderDAOImpl() {
    }

    public static ReaderDAO getInstance() {
        ReaderDAO readerDAO = INSTANCE;
        if (readerDAO == null) {
            synchronized (ReaderDAOImpl.class) {
                readerDAO = INSTANCE;
                if (readerDAO == null) {
                    INSTANCE = readerDAO = new ReaderDAOImpl();
                }
            }
        }

        return readerDAO;
    }


    @Override
    public List<Reader> getBySurname(String surname) throws SQLException {
        log.info("Get readers by surname:" + surname);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Reader where SURNAME = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Reader> getByStatus(String status) throws SQLException {
        log.info("Get readers by status:" + status);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Reader where STATUS = :status");
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<Reader> getByLogin(String login) throws SQLException {
        log.info("Get readers by login:" + login);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Reader where EMAIL = :login");
        query.setParameter("login", login);
        return query.getResultList();

    }


}

