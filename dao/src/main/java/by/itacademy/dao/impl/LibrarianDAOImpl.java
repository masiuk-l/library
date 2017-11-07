package by.itacademy.dao.impl;

import by.itacademy.dao.LibrarianDAO;
import by.itacademy.entities.Librarian;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of LibrarianDAO interface
 */
@Log4j
@Repository
public class LibrarianDAOImpl extends BaseDAOImpl<Librarian> implements LibrarianDAO {

    @Override
    public List<Librarian> getByLogin(String login) {
        log.info("Get librarians by login:" + login);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Librarian where EMAIL = :login");
        query.setParameter("login", login);
        return query.getResultList();
    }

}
