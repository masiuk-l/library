package by.itacademy.impl;

import by.itacademy.auth.Encoder;
import by.itacademy.ReaderDAO;
import by.itacademy.ConnectionManager;
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
 * Implementation of ReaderDAO interface
 */

@Log4j
public class ReaderDAOImpl extends AbstractDAO implements ReaderDAO {
    private static final String saveReaderQuery = "INSERT INTO READERS (SURNAME, NAME, SECOND_NAME, PASSWORD, EMAIL, BIRTHDAY, GENDER, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateReaderQuery = "UPDATE READERS SET SURNAME=?, NAME=?, SECOND_NAME=?, PASSWORD=?, EMAIL=?, BIRTHDAY=?, GENDER=?, STATUS=? WHERE READER_ID=?";
    private static final String getReaderQuery = "SELECT * FROM READERS WHERE READER_ID=?";
    private static final String getAllReaderQuery = "SELECT * FROM READERS";
    private static final String getReaderBySurnameQuery = "SELECT * FROM READERS WHERE SURNAME=?";
    private static final String getReaderByLoginQuery = "SELECT * FROM READERS WHERE EMAIL=?";
    private static final String getReaderByStatusQuery = "SELECT * FROM READERS WHERE STATUS=?";
    private static final String deleteReaderQuery = "DELETE FROM READERS WHERE READER_ID=?";
    private static volatile ReaderDAO INSTANCE = null;
    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetBySurname;
    private PreparedStatement psGetByLogin;
    private PreparedStatement psGetByStatus;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveReaderQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateReaderQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getReaderQuery);
            psGetBySurname = ConnectionManager.getConnection().prepareStatement(getReaderBySurnameQuery);
            psGetByLogin = ConnectionManager.getConnection().prepareStatement(getReaderByLoginQuery);
            psGetByStatus = ConnectionManager.getConnection().prepareStatement(getReaderByStatusQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllReaderQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteReaderQuery);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private ReaderDAOImpl() {
    }

    public static ReaderDAO getInstance() {
        ReaderDAO readerDAO = INSTANCE;
        if (readerDAO == null) {
            synchronized (ReaderDAOImpl.class) {
                readerDAO = INSTANCE;
                if (readerDAO == null) {
                    INSTANCE = readerDAO = new ReaderDAOImpl();
                }
            }
        }

        return readerDAO;
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
    public Reader save(Reader reader) throws SQLException {
        psSave.setString(1, reader.getSurname());
        psSave.setString(2, reader.getName());
        psSave.setString(3, reader.getSecondName());
        psSave.setString(4, Encoder.encode(reader.getPassword()));
        psSave.setString(5, reader.getEmail());
        psSave.setDate(6, toSQLDate(reader.getBirthday()));
        psSave.setString(7, reader.getGender());
        psSave.setString(8, reader.getStatus());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            reader.setReaderID(rs.getInt(1));
        }
        close(rs);
        return reader;
    }

    @Override
    public Reader get(Serializable id) throws SQLException {
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateReader(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Reader reader) throws SQLException {
        psUpdate.setInt(9, reader.getReaderID());
        psUpdate.setString(1, reader.getSurname());
        psUpdate.setString(2, reader.getName());
        psUpdate.setString(3, reader.getSecondName());
        psUpdate.setString(4, reader.getPassword());
        psUpdate.setString(5, reader.getEmail());
        psUpdate.setDate(6, toSQLDate(reader.getBirthday()));
        psUpdate.setString(7, reader.getGender());
        psUpdate.setString(8, reader.getStatus());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Reader> getBySurname(String surname) throws SQLException {
        List<Reader> list = new ArrayList<>();
        psGetBySurname.setString(1, surname);
        psGetBySurname.execute();
        ResultSet rs = psGetBySurname.getResultSet();
        while (rs.next()) {
            list.add(populateReader(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Reader> getByStatus(String status) throws SQLException {
        List<Reader> list = new ArrayList<>();
        psGetByStatus.setString(1, status);
        psGetByStatus.execute();
        ResultSet rs = psGetByStatus.getResultSet();
        while (rs.next()) {
            list.add(populateReader(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Reader> getByLogin(String login) throws SQLException {
        List<Reader> list = new ArrayList<>();
        psGetByLogin.setString(1, login);
        psGetByLogin.execute();
        ResultSet rs = psGetByLogin.getResultSet();
        while (rs.next()) {
            list.add(populateReader(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Reader> getAll() throws SQLException {
        List<Reader> list = new ArrayList<>();
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateReader(rs));
        }
        close(rs);
        return list;
    }

    private Reader populateReader(ResultSet rs) throws SQLException {
        Reader reader = new Reader();
        reader.setReaderID(rs.getInt(1));
        reader.setSurname(rs.getString(2));
        reader.setName(rs.getString(3));
        reader.setSecondName(rs.getString(4));
        reader.setPassword(rs.getString(5));
        reader.setEmail(rs.getString(6));
        reader.setBirthday(toLocalDate(rs.getDate(7)));
        reader.setGender(rs.getString(8));
        reader.setStatus(rs.getString(9));

        return reader;
    }
}

