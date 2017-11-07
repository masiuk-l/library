package by.itacademy.dao;

import by.itacademy.entities.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookDAO extends CrudRepository<Book, Integer> {


//    List<Book> getCatalogPage(int pageNumber, int size);//todo переделать

}
