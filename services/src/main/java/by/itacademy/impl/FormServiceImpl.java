package by.itacademy.impl;

import by.itacademy.FormService;
import by.itacademy.ServiceException;
import by.itacademy.dao.FormDAO;
import by.itacademy.dao.impl.FormDAOImpl;
import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of FormService
 */
public class FormServiceImpl extends AbstractService implements FormService {
    private static volatile FormService INSTANCE = null;

    private FormDAO formDAO = FormDAOImpl.getInstance();

    private FormServiceImpl() {
    }

    public static FormService getInstance() {
        FormService formService = INSTANCE;
        if (formService == null) {
            synchronized (FormServiceImpl.class) {
                formService = INSTANCE;
                if (formService == null) {
                    INSTANCE = formService = new FormServiceImpl();
                }
            }
        }

        return formService;
    }

    @Override
    public Form save(Form form) {
        try {
            if (form != null) {
                startTransaction();
                form = formDAO.save(form);
                commit();
                return form;
            } else {
                throw new ServiceException("Form not defined");
            }
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error creating Form", e);
        }

    }

    @Override
    public Form get(Serializable id) {
        try {
            Form form;
            startTransaction();
            form = formDAO.get(id);
            commit();
            return form;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error getting Form", e);
        }
    }

    @Override
    public void update(Form form) {
        try {
            startTransaction();
            formDAO.update(form);
            commit();
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error updating Form", e);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            int rows = formDAO.delete(id);
            commit();
            return rows;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error deleting Form", e);
        }
    }

    @Override
    public List<Form> getByReader(Reader reader) {
        ArrayList<Form> forms;
        try {
            startTransaction();
            forms = new ArrayList<>(formDAO.getByReader(reader));
            commit();
            return forms;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Form", e);
        }
    }


    @Override
    public List<Form> getByBook(Book book) {
        ArrayList<Form> forms;
        try {
            startTransaction();
            forms = new ArrayList<>(formDAO.getByBook(book));
            commit();
            return forms;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Form", e);
        }
    }

    @Override
    public List<Form> getAll() {
        ArrayList<Form> forms;
        try {
            startTransaction();
            forms = new ArrayList<>(formDAO.getAll());
            commit();
            return forms;
        } catch (HibernateException | SQLException e) {
            rollback();
            throw new ServiceException("Error finding Form", e);
        }
    }
}
