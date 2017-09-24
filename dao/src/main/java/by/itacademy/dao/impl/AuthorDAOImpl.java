package by.itacademy.dao.impl;

import by.itacademy.dao.AuthorDAO;
import by.itacademy.db.ConnectionManager;
import by.itacademy.entities.Author;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 * <p>
 * Implementation of AuthorDAO interface
 */

@Log4j
public class AuthorDAOImpl extends AbstractDAO implements AuthorDAO {

    private static final String SAVE_AUTHOR_QUERY = "INSERT INTO AUTHORS (SURNAME, NAME, SECOND_NAME, BIRTHDAY, COUNTRY) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_AUTHOR_QUERY = "UPDATE AUTHORS SET SURNAME=?, NAME=?, SECOND_NAME=?, BIRTHDAY=? ,COUNTRY=? WHERE AUTHOR_ID=?";
    private static final String GET_AUTHOR_QUERY = "SELECT * FROM AUTHORS WHERE Author_ID=?";
    private static final String GET_ALL_AUTHORS_QUERY = "SELECT * FROM AUTHORS";
    private static final String GET_AUTHOR_BY_SURNAME_QUERY = "SELECT * FROM AUTHORS WHERE SURNAME=?";
    private static final String DELETE_AUTHOR_QUERY = "DELETE FROM AUTHORS WHERE AUTHOR_ID=?";
    private static volatile AuthorDAO INSTANCE = null;
    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetBySurname;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(SAVE_AUTHOR_QUERY, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(UPDATE_AUTHOR_QUERY);
            psGet = ConnectionManager.getConnection().prepareStatement(GET_AUTHOR_QUERY);
            psGetBySurname = ConnectionManager.getConnection().prepareStatement(GET_AUTHOR_BY_SURNAME_QUERY);
            psGetAll = ConnectionManager.getConnection().prepareStatement(GET_ALL_AUTHORS_QUERY);
            psDelete = ConnectionManager.getConnection().prepareStatement(DELETE_AUTHOR_QUERY);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private AuthorDAOImpl() {
    }

    public static AuthorDAO getInstance() {
        AuthorDAO authorDAO = INSTANCE;
        if (authorDAO == null) {
            synchronized (AuthorDAOImpl.class) {
                authorDAO = INSTANCE;
                if (authorDAO == null) {
                    INSTANCE = authorDAO = new AuthorDAOImpl();
                }
            }
        }

        return authorDAO;
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
    public Author save(Author author) throws SQLException {
        psSave.setString(1, author.getSurname());
        psSave.setString(2, author.getName());
        psSave.setString(3, author.getSecondName());
        psSave.setDate(4, toSQLDate(author.getBirthday()));
        psSave.setString(5, author.getCountry());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            author.setAuthorID(rs.getInt(1));
        }
        close(rs);
        return author;
    }

    @Override
    public Author get(Serializable id) throws SQLException {
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateAuthor(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Author author) throws SQLException {
        psUpdate.setInt(6, author.getAuthorID());
        psUpdate.setString(1, author.getSurname());
        psUpdate.setString(2, author.getName());
        psUpdate.setString(3, author.getSecondName());
        psUpdate.setDate(4, toSQLDate(author.getBirthday()));
        psUpdate.setString(5, author.getCountry());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Author> getBySurname(String surname) throws SQLException {
        List<Author> list = new ArrayList<>();
        psGetBySurname.setString(1, surname);
        psGetBySurname.execute();
        ResultSet rs = psGetBySurname.getResultSet();
        while (rs.next()) {
            list.add(populateAuthor(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Author> getAll() throws SQLException {
        List<Author> list = new ArrayList<>();
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateAuthor(rs));
        }
        close(rs);
        return list;
    }

    private Author populateAuthor(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setAuthorID(rs.getInt(1));
        author.setSurname(rs.getString(2));
        author.setName(rs.getString(3));
        author.setSecondName(rs.getString(4));
        author.setBirthday(toLocalDate(rs.getDate(5)));
        author.setCountry(rs.getString(6));
        return author;
    }
}
