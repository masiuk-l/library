package by.itacademy.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * general DAO interface
 */
public interface DAO<T> {

    /**
     * @param t entity to save
     * @return saved entity
     */
    T save(T t);

    /**
     * @param id id of the entity
     * @return entity
     */
    T get(Serializable id);

    /**
     * @param t entity to update
     */
    void update(T t);

    /**
     * @param id id of the entity
     * @return affected rows
     */
    int delete(Serializable id);

    /**
     * @return list of all entities
     */
    List<T> getAll();

}
