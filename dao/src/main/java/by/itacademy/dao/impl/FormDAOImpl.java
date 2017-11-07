package by.itacademy.dao.impl;

import by.itacademy.dao.FormDAO;
import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of FormAuthorDAO interface
 */

@Log4j
@Repository
public class FormDAOImpl extends BaseDAOImpl<Form> implements FormDAO {

    @Override
    public List<Form> getByBook(Book book) {
        log.info("Get forms by book:" + book);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Form where BOOK_ID = :bookID");
        query.setParameter("bookID", book.getBookID());
        return query.getResultList();
    }

    @Override
    public List<Form> getByReader(Reader reader) {
        log.info("Get forms by reader:" + reader);
        Session session = getSession();
        javax.persistence.Query query = session.createQuery("from Form where READER_ID = :readerID");
        query.setParameter("readerID", reader.getReaderID());
        return query.getResultList();
    }

}
