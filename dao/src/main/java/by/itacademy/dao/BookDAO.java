package by.itacademy.dao;

import by.itacademy.entities.Book;
import by.itacademy.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookDAO extends JpaRepository<Book, Integer> {

    @Query("select b from Book b join b.forms f where f.reader= ?1")
    List<Book> getByReader(Reader reader);//todo переделать

}
