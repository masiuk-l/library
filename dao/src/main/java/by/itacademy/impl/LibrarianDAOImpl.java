package by.itacademy.impl;

import by.itacademy.ConnectionManager;
import by.itacademy.Librarian;
import by.itacademy.LibrarianDAO;
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
 * <p>
 * Implementation of LibrarianDAO interface
 */
@Log4j
public class LibrarianDAOImpl implements LibrarianDAO {

    private static final String saveLibrarianQuery = "INSERT INTO LIBRARIANS (SURNAME, NAME, SECOND_NAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?, ?)";
    private static final String updateLibrarianQuery = "UPDATE LIBRARIANS SET SURNAME=?, NAME=?, SECOND_NAME=?, PASSWORD=?, EMAIL=? WHERE LIBRARIAN_ID=?";
    private static final String getLibrarianQuery = "SELECT * FROM LIBRARIANS WHERE LIBRARIAN_ID=?";
    private static final String getAllLibrarianQuery = "SELECT * FROM LIBRARIANS";
    private static final String getLibrarianBySurnameQuery = "SELECT * FROM LIBRARIANS WHERE SURNAME=?";
    private static final String getLibrarianByLoginQuery = "SELECT * FROM LIBRARIANS WHERE EMAIL=?";
    private static final String deleteLibrarianQuery = "DELETE FROM LIBRARIANS WHERE LIBRARIAN_ID=?";
    private static volatile LibrarianDAO INSTANCE = null;
    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetBySurname;
    private PreparedStatement psGetByLogin;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveLibrarianQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateLibrarianQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getLibrarianQuery);
            psGetBySurname = ConnectionManager.getConnection().prepareStatement(getLibrarianBySurnameQuery);
            psGetByLogin = ConnectionManager.getConnection().prepareStatement(getLibrarianByLoginQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllLibrarianQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteLibrarianQuery);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private LibrarianDAOImpl() {
    }

    public static LibrarianDAO getInstance() {
        LibrarianDAO librarianDAO = INSTANCE;
        if (librarianDAO == null) {
            synchronized (LibrarianDAOImpl.class) {
                librarianDAO = INSTANCE;
                if (librarianDAO == null) {
                    INSTANCE = librarianDAO = new LibrarianDAOImpl();
                }
            }
        }

        return librarianDAO;
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
    public Librarian save(Librarian librarian) throws SQLException {
        psSave.setString(1, librarian.getSurname());
        psSave.setString(2, librarian.getName());
        psSave.setString(3, librarian.getSecondName());
        psSave.setString(4, librarian.getPassword());
        psSave.setString(5, librarian.getEmail());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            librarian.setLibrarianID(rs.getInt(1));
        }
        close(rs);
        return librarian;
    }

    @Override
    public Librarian get(Serializable id) throws SQLException {
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateLibrarian(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Librarian librarian) throws SQLException {
        psUpdate.setInt(6, librarian.getLibrarianID());
        psUpdate.setString(1, librarian.getSurname());
        psUpdate.setString(2, librarian.getName());
        psUpdate.setString(3, librarian.getSecondName());
        psUpdate.setString(4, librarian.getPassword());
        psUpdate.setString(5, librarian.getEmail());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Librarian> getBySurname(String surname) throws SQLException {
        List<Librarian> list = new ArrayList<>();
        psGetBySurname.setString(1, surname);
        psGetBySurname.execute();
        ResultSet rs = psGetBySurname.getResultSet();
        while (rs.next()) {
            list.add(populateLibrarian(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Librarian> getByLogin(String login) throws SQLException {
        List<Librarian> list = new ArrayList<>();
        psGetByLogin.setString(1, login);
        psGetByLogin.execute();
        ResultSet rs = psGetByLogin.getResultSet();
        while (rs.next()) {
            list.add(populateLibrarian(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Librarian> getAll() throws SQLException {
        List<Librarian> list = new ArrayList<>();
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateLibrarian(rs));
        }
        close(rs);
        return list;
    }

    private Librarian populateLibrarian(ResultSet rs) throws SQLException {
        Librarian librarian = new Librarian();
        librarian.setLibrarianID(rs.getInt(1));
        librarian.setSurname(rs.getString(2));
        librarian.setName(rs.getString(3));
        librarian.setSecondName(rs.getString(4));
        librarian.setPassword(rs.getString(5));
        librarian.setEmail(rs.getString(6));

        return librarian;
    }
}
