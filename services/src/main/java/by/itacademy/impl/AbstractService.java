package by.itacademy.impl;

import by.itacademy.util.SFUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Transaction util methods
 */
public abstract class AbstractService {
    private Transaction transaction;

    public Transaction startTransaction() throws SQLException {
        transaction = getSession().getTransaction();
        transaction.begin();
        return transaction;
    }

    public void commit() throws SQLException {
        transaction.commit();
    }

    public Session getSession() {
        return SFUtil.getSession();
    }

    public void rollback() {
        transaction.rollback();

    }
}
