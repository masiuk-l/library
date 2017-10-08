package by.itacademy.impl;

import by.itacademy.util.SFUtil;
import org.hibernate.Session;

import java.sql.SQLException;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Transaction util methods
 */
public abstract class AbstractService {

    public void startTransaction() throws SQLException {
        getSession().getTransaction().begin();
    }

    public void commit() throws SQLException {
        getSession().getTransaction().commit();
    }

    public Session getSession() {
        return SFUtil.getSession();
    }

    public void rollback() {
        getSession().getTransaction().rollback();

    }
}
