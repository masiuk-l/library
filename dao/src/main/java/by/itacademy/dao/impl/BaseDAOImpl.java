package by.itacademy.dao.impl;

import by.itacademy.dao.DAO;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Base implementation of DAO interface
 */

@Log4j
@Repository
@Primary
public abstract class BaseDAOImpl<T> implements DAO<T> {

    @PersistenceContext
    @Getter
    private EntityManager em;

    public Session getSession() {
        return em.unwrap(Session.class);
    }

}
