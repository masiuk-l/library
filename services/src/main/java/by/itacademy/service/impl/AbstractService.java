package by.itacademy.service.impl;

import by.itacademy.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Transaction util methods
 */
public abstract class AbstractService {

    public void startTransaction() {
        HibernateUtil.beginTransaction();
    }

    public void commit() throws SQLException {
        HibernateUtil.commit();
    }

    public Session getSession() {
        return HibernateUtil.getEntityManager().unwrap(Session.class);
    }

    public void rollback() {
        HibernateUtil.rollback();

    }
}
