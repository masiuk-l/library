package by.itacademy.impl;

import by.itacademy.Author;
import by.itacademy.AuthorDAO;
import by.itacademy.AuthorService;
import by.itacademy.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of AuthorService
 */
public class AuthorServiceImpl extends AbstractService implements AuthorService {
    private static volatile AuthorService INSTANCE = null;
    private AuthorDAO authorDAO = AuthorDAOImpl.getInstance();

    private AuthorServiceImpl() {
    }

    public static AuthorService getInstance() {
        AuthorService authorService = INSTANCE;
        if (authorService == null) {
            synchronized (AuthorServiceImpl.class) {
                authorService = INSTANCE;
                if (authorService == null) {
                    INSTANCE = authorService = new AuthorServiceImpl();
                }
            }
        }

        return authorService;
    }

    @Override
    public Author save(Author author) {
        try {
            if (author != null) {
                startTransaction();
                author = authorDAO.save(author);
                commit();
                return author;
            } else {
                throw new ServiceException("Author not defined");
            }
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Author", e);
        }

    }

    @Override
    public Author get(Serializable id) {
        try {
            Author author;
            startTransaction();
            author = authorDAO.get(id);
            commit();
            return author;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Author", e);
        }
    }

    @Override
    public void update(Author author) {
        try {
            startTransaction();
            authorDAO.update(author);
            commit();
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error updating Author", e);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            int rows = authorDAO.delete(id);
            commit();
            return rows;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error deleting Author", e);
        }
    }

    @Override
    public List<Author> getBySurname(String surname) {
        ArrayList<Author> authors;
        try {
            startTransaction();
            authors = new ArrayList<>(authorDAO.getBySurname(surname));
            commit();
            return authors;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding Author", e);
        }
    }

    @Override
    public List<Author> getAll() {
        ArrayList<Author> authors;
        try {
            startTransaction();
            authors = new ArrayList<>(authorDAO.getAll());
            commit();
            return authors;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding Author", e);
        }
    }
}
