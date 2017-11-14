package by.itacademy.service.impl;

import by.itacademy.dao.DAO;
import by.itacademy.service.IService;

import java.util.List;

public class IServiceImpl<T> implements IService<T> {

    protected DAO dao;

    @Override
    public T save(T t) {
        return (T) dao.save(t);
    }

    @Override
    public T get(Integer id) {
        return (T) dao.findOne(id);
    }

    @Override
    public void update(T t) {
        dao.save(t);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }
}
