package by.itacademy.dao.impl;

import by.itacademy.dao.ReaderDAO;
import by.itacademy.entities.Reader;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of ReaderDAO interface
 */

@Log4j
@Repository
public class ReaderDAOImpl extends BaseDAOImpl<Reader> implements ReaderDAO {

    @Override
    public List<Reader> getByLogin(String login) {
        log.info("Get readers by login:" + login);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Reader where EMAIL = :login");
        query.setParameter("login", login);
        return query.getResultList();

    }


}

