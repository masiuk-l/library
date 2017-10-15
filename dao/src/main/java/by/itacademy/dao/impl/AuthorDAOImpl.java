package by.itacademy.dao.impl;

import by.itacademy.dao.AuthorDAO;
import by.itacademy.entities.Author;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 * <p>
 * Implementation of AuthorDAO interface
 */

@Log4j
public class AuthorDAOImpl extends BaseDAOImpl<Author> implements AuthorDAO {

    private static volatile AuthorDAO INSTANCE = null;

    private AuthorDAOImpl() {
    }

    public static AuthorDAO getInstance() {
        AuthorDAO authorDAO = INSTANCE;
        if (authorDAO == null) {
            synchronized (AuthorDAOImpl.class) {
                authorDAO = INSTANCE;
                if (authorDAO == null) {
                    INSTANCE = authorDAO = new AuthorDAOImpl();
                }
            }
        }

        return authorDAO;
    }


    @Override
    public List<Author> getBySurname(String surname) throws SQLException {
        log.info("Get authors by surname:" + surname);
        Session session = getSession();
        Query query = session.createQuery("from Author a where a.surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

}
