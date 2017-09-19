package by.itacademy;

import java.io.Serializable;
import java.util.List;

public interface Service<T> {

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
