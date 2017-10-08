package by.itacademy.dao.impl;

import by.itacademy.dao.DAO;
import by.itacademy.util.SFUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Base implementation of DAO interface
 */

@Log4j
public abstract class BaseDAOImpl<T> implements DAO<T> {


    public Session getSession() {
        return SFUtil.getSession();
    }

    @Override
    public T save(T t) throws SQLException {
        getSession().save(t);
        log.info("Save:" + t);
        return t;
    }

    @Override
    public T get(Serializable id) throws SQLException {
        log.info("Get:" + id);
        return (T) getSession().load(getPersistentClass(), id);
    }

    @Override
    public void update(T t) throws SQLException {
        getSession().update(t);
        log.info("Update:" + t);
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        log.info("Delete:" + id);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("delete from" + getPersistentClass().getName().toUpperCase() + "where id=:id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public List<T> getAll() throws SQLException {
        log.info("Get all:" + getPersistentClass().getName());
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from" + getPersistentClass().getName().toUpperCase());
        return query.getResultList();
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
