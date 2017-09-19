package by.itacademy.impl;

import by.itacademy.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of BookAuthorService
 */
public class BookAuthorServiceImpl extends AbstractService implements BookAuthorService {

    private static volatile BookAuthorService INSTANCE = null;

    private BookAuthorDAO bookAuthorDAO = BookAuthorDAOImpl.getInstance();

    private BookAuthorServiceImpl() {
    }

    public static BookAuthorService getInstance() {
        BookAuthorService bookAuthorService = INSTANCE;
        if (bookAuthorService == null) {
            synchronized (BookAuthorServiceImpl.class) {
                bookAuthorService = INSTANCE;
                if (bookAuthorService == null) {
                    INSTANCE = bookAuthorService = new BookAuthorServiceImpl();
                }
            }
        }

        return bookAuthorService;
    }

    @Override
    public BookAuthor save(BookAuthor bookAuthor) {
        try {
            if (bookAuthor != null) {
                startTransaction();
                bookAuthor = bookAuthorDAO.save(bookAuthor);
                commit();
                return bookAuthor;
            } else {
                throw new ServiceException("BookAuthor not defined");
            }
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating BookAuthor", e);
        }

    }

    @Override
    public BookAuthor get(Serializable id) {
        try {
            BookAuthor bookAuthor;
            startTransaction();
            bookAuthor = bookAuthorDAO.get(id);
            commit();
            return bookAuthor;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting BookAuthor", e);
        }
    }

    @Override
    public void update(BookAuthor bookAuthor) {
        try {
            startTransaction();
            bookAuthorDAO.update(bookAuthor);
            commit();
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error updating BookAuthor", e);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            int rows = bookAuthorDAO.delete(id);
            commit();
            return rows;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error deleting BookAuthor", e);
        }
    }

    @Override
    public List<BookAuthor> getByAuthorID(Author author) {
        ArrayList<BookAuthor> bookAuthors;
        try {
            startTransaction();
            bookAuthors = new ArrayList<>(bookAuthorDAO.getByAuthorID(author));
            commit();
            return bookAuthors;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding BookAuthor", e);
        }
    }

    @Override
    public List<BookAuthor> getByBookID(Book book) {
        ArrayList<BookAuthor> bookAuthors;
        try {
            startTransaction();
            bookAuthors = new ArrayList<>(bookAuthorDAO.getByBookID(book));
            commit();
            return bookAuthors;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding BookAuthor", e);
        }
    }

    @Override
    public List<BookAuthor> getAll() {
        ArrayList<BookAuthor> bookAuthors;
        try {
            startTransaction();
            bookAuthors = new ArrayList<>(bookAuthorDAO.getAll());
            commit();
            return bookAuthors;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding BookAuthor", e);
        }
    }
}

