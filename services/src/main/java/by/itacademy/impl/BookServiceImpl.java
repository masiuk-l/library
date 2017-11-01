package by.itacademy.impl;

import by.itacademy.BookService;
import by.itacademy.ServiceException;
import by.itacademy.dao.AuthorDAO;
import by.itacademy.dao.BookDAO;
import by.itacademy.dao.FormDAO;
import by.itacademy.dao.ReaderDAO;
import by.itacademy.dao.impl.AuthorDAOImpl;
import by.itacademy.dao.impl.BookDAOImpl;
import by.itacademy.dao.impl.FormDAOImpl;
import by.itacademy.dao.impl.ReaderDAOImpl;
import by.itacademy.entities.Book;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of BookService
 */
public class BookServiceImpl extends AbstractService implements BookService {
    private static volatile BookService INSTANCE = null;

    private BookDAO bookDAO = BookDAOImpl.getInstance();
    private ReaderDAO readerDAO = ReaderDAOImpl.getInstance();
    private AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
    private FormDAO formDAO = FormDAOImpl.getInstance();

    private BookServiceImpl() {
    }

    public static BookService getInstance() {
        BookService BookService = INSTANCE;
        if (BookService == null) {
            synchronized (BookServiceImpl.class) {
                BookService = INSTANCE;
                if (BookService == null) {
                    INSTANCE = BookService = new BookServiceImpl();
                }
            }
        }

        return BookService;
    }

    @Override
    public Book save(Book book) {
        try {
            if (book != null) {
                startTransaction();
                book = bookDAO.save(book);
                commit();
                return book;
            } else {
                throw new ServiceException("Book not defined");
            }
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error creating Book", e);
        }

    }

    @Override
    public Book get(Serializable id) {
        try {
            Book book;
            startTransaction();
            book = bookDAO.get(id);
            commit();
            return book;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error getting Book", e);
        }
    }

    @Override
    public void update(Book book) {
        try {
            startTransaction();
            bookDAO.update(book);
            commit();
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error updating Book", e);
        }
    }

    @Override
    public void update(Book oldBook, Book newBook) {
        Book book = new Book();
        book.setBookID(oldBook.getBookID());
        book.setName((newBook.getName().length() == 0) ? oldBook.getName() : newBook.getName());
        book.setIsbn((newBook.getIsbn().length() == 0) ? oldBook.getIsbn() : newBook.getIsbn());
        book.setGenre((newBook.getGenre().length() == 0) ? oldBook.getGenre() : newBook.getGenre());
        book.setYear((newBook.getYear() == 0) ? oldBook.getYear() : newBook.getYear());
        book.setQuantity(newBook.getQuantity());
        update(book);
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            Book book = bookDAO.get(id);
            int rows = bookDAO.delete(id);
            commit();
            return rows;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error deleting Book", e);
        }
    }

    @Override
    public List<Book> getByName(String name) {
        ArrayList<Book> books;
        try {
            startTransaction();
            books = new ArrayList<>(bookDAO.getByName(name));
            commit();
            return books;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Book", e);
        }
    }

    @Override
    public List<Book> searchByName(String name) {
        String searchKey = name.toLowerCase();
        ArrayList<Book> books = new ArrayList<>();
        try {
            startTransaction();
            ArrayList<Book> allBooks = new ArrayList<>(bookDAO.getAll());
            for (Book aBook : allBooks) {
                if (aBook.getName().toLowerCase().contains(searchKey) || aBook.getGenre().toLowerCase().contains(searchKey))
                    books.add(aBook);
            }
            commit();
            return books;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Book", e);
        }
    }

    @Override
    public List<Book> getByIsbn(String isbn) {
        ArrayList<Book> books;
        try {
            startTransaction();
            books = new ArrayList<>(bookDAO.getByIsbn(isbn));
            commit();
            return books;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Book", e);
        }
    }

    @Override
    public List<Book> getByGenre(String genre) {
        ArrayList<Book> books;
        try {
            startTransaction();
            books = new ArrayList<>(bookDAO.getByGenre(genre));
            commit();
            return books;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Book", e);
        }
    }


    @Override
    public List<Book> getAll() {
        ArrayList<Book> books;
        try {
            startTransaction();
            books = new ArrayList<>(bookDAO.getAll());
            commit();
            return books;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Book", e);
        }
    }

    @Override
    public List<Book> getCatalogPage(int pageNumber, int size) {
        ArrayList<Book> books;
        try {
            startTransaction();
            books = new ArrayList<>(bookDAO.getCatalogPage(pageNumber, size));
            commit();
            return books;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Book", e);
        }
    }

}
