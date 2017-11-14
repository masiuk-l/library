package by.itacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface DAO<T, ID extends Serializable> extends JpaRepository<T, ID> {
    @Override
    List<T> findAll();

    @Override
    <S extends T> S save(S s);

    @Override
    T findOne(ID id);

    @Override
    void deleteAll();
}
