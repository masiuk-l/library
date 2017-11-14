package by.itacademy.service;

import java.util.List;

public interface IService<T> {

    /**
     * @param t entity to save
     * @return saved entity
     */
    T save(T t);

    /**
     * @param id id of the entity
     * @return entity
     */
    T get(Integer id);

    /**
     * @param t entity to update
     */
    void update(T t);

    /**
     * @param id id of the entity
     */
    void delete(Integer id);

    /**
     * @return list of all entities
     */
    List<T> getAll();
}
