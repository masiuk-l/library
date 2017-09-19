package by.itacademy.impl;

import by.itacademy.FormDAO;
import by.itacademy.ConnectionManager;
import by.itacademy.Book;
import by.itacademy.Form;
import by.itacademy.Librarian;
import by.itacademy.Reader;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 *
 * Implementation of FormAuthorDAO interface
 */

@Log4j
public class FormDAOImpl extends AbstractDAO implements FormDAO {


    private static final String saveFormQuery = "INSERT INTO FORMS (BOOK_ID, READER_ID, LIBRARIAN_ID, RECEIVAL_TYPE, RECEIVAL_DATE, RETURN_DATE) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String updateFormQuery = "UPDATE FORMS SET BOOK_ID=?, READER_ID=?, LIBRARIAN_ID=?, RECEIVAL_TYPE=?, RECEIVAL_DATE=?, RETURN_DATE=? WHERE FORM_ID=?";
    private static final String getFormQuery = "SELECT * FROM FORMS WHERE FORM_ID=?";
    private static final String getAllFormQuery = "SELECT * FROM FORMS";
    private static final String getFormByReaderIDQuery = "SELECT * FROM FORMS WHERE READER_ID=?";
    private static final String getFormByBookIDQuery = "SELECT * FROM FORMS WHERE BOOK_ID=?";
    private static final String getFormByLibrarianIDQuery = "SELECT * FROM FORMS WHERE LIBRARIAN_ID=?";
    private static final String getFormByReceivalTypeQuery = "SELECT * FROM FORMS WHERE RECEIVAL_TYPE=?";
    private static final String deleteFormQuery = "DELETE FROM FORMS WHERE FORM_ID=?";
    private static volatile FormDAO INSTANCE = null;
    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetByReaderID;
    private PreparedStatement psGetByReceivalType;
    private PreparedStatement psGetByLibrarianID;
    private PreparedStatement psGetByBookID;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveFormQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateFormQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getFormQuery);
            psGetByBookID = ConnectionManager.getConnection().prepareStatement(getFormByBookIDQuery);
            psGetByReaderID = ConnectionManager.getConnection().prepareStatement(getFormByReaderIDQuery);
            psGetByLibrarianID = ConnectionManager.getConnection().prepareStatement(getFormByLibrarianIDQuery);
            psGetByReceivalType = ConnectionManager.getConnection().prepareStatement(getFormByReceivalTypeQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllFormQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteFormQuery);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private FormDAOImpl() {
    }

    public static FormDAO getInstance() {
        FormDAO formDAO = INSTANCE;
        if (formDAO == null) {
            synchronized (FormDAOImpl.class) {
                formDAO = INSTANCE;
                if (formDAO == null) {
                    INSTANCE = formDAO = new FormDAOImpl();
                }
            }
        }

        return formDAO;
    }

    private static void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public Form save(Form form) throws SQLException {
        psSave.setInt(1, form.getBookID());
        psSave.setInt(2, form.getReaderID());
        psSave.setInt(3, form.getLibrarianID());
        psSave.setString(4, form.getReceivalType());
        psSave.setDate(5, toSQLDate(form.getReceivalDate()));
        psSave.setDate(6, toSQLDate(form.getReturnDate()));
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            form.setFormID(rs.getInt(1));
        }
        close(rs);
        return form;
    }

    @Override
    public Form get(Serializable id) throws SQLException {
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateForm(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Form form) throws SQLException {
        psUpdate.setInt(7, form.getFormID());
        psUpdate.setInt(1, form.getBookID());
        psUpdate.setInt(2, form.getReaderID());
        psUpdate.setInt(3, form.getLibrarianID());
        psUpdate.setString(4, form.getReceivalType());
        psUpdate.setDate(5, toSQLDate(form.getReceivalDate()));
        psUpdate.setDate(6, toSQLDate(form.getReturnDate()));
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Form> getByBook(Book book) throws SQLException {
        List<Form> list = new ArrayList<>();
        psGetByBookID.setInt(1, book.getBookID());
        psGetByBookID.execute();
        ResultSet rs = psGetByBookID.getResultSet();
        while (rs.next()) {
            list.add(populateForm(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Form> getByReader(Reader reader) throws SQLException {
        List<Form> list = new ArrayList<>();
        psGetByReaderID.setInt(1, reader.getReaderID());
        psGetByReaderID.execute();
        ResultSet rs = psGetByReaderID.getResultSet();
        while (rs.next()) {
            list.add(populateForm(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Form> getByLibrarian(Librarian librarian) throws SQLException {
        List<Form> list = new ArrayList<>();
        psGetByLibrarianID.setInt(1, librarian.getLibrarianID());
        psGetByLibrarianID.execute();
        ResultSet rs = psGetByLibrarianID.getResultSet();
        while (rs.next()) {
            list.add(populateForm(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Form> getByReceivalType(String receivalType) throws SQLException {
        List<Form> list = new ArrayList<>();
        psGetByReceivalType.setString(1, receivalType);
        psGetByReceivalType.execute();
        ResultSet rs = psGetByReceivalType.getResultSet();
        while (rs.next()) {
            list.add(populateForm(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Form> getAll() throws SQLException {
        List<Form> list = new ArrayList<>();
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateForm(rs));
        }
        close(rs);
        return list;
    }

    private Form populateForm(ResultSet rs) throws SQLException {
        Form form = new Form();
        form.setFormID(rs.getInt(1));
        form.setBookID(rs.getInt(2));
        form.setReaderID(rs.getInt(3));
        form.setLibrarianID(rs.getInt(4));
        form.setReceivalType(rs.getString(5));
        form.setReceivalDate(toLocalDate(rs.getDate(6)));
        form.setReturnDate(toLocalDate(rs.getDate(7)));

        return form;
    }
}
