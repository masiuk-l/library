package by.itacademy.dao;

import by.itacademy.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 */
public interface BookDAO extends PagingAndSortingRepository<Book, Integer> {


//   List<Book> ggetCatalogPage(int pageNumber, int size);//todo переделать

}
