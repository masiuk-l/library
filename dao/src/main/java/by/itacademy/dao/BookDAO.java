package by.itacademy.dao;

import by.itacademy.entities.Book;
import by.itacademy.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookDAO extends JpaRepository<Book, Integer>, DAO<Book> {

    @Query("select r from Reader r join r.forms f where f.book= ?1")
    List<Reader> getBookReaders(Book book);

}
