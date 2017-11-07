package by.itacademy.dao.impl;

import by.itacademy.dao.BookDAO;
import by.itacademy.entities.Book;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of BookDAO interface
 */

@Log4j
@Repository
public class BookDAOImpl extends BaseDAOImpl<Book> implements BookDAO {

    @Override
    public List<Book> getCatalogPage(int pageNumber, int size) {
        log.info("Get catalog page:" + pageNumber);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Book");
        query.setFirstResult(size * (pageNumber - 1));
        query.setMaxResults(size);
        return query.getResultList();
    }

}

