package by.itacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface DAO<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
