package by.itacademy.dao;

import by.itacademy.dao.impl.AuthorDAOImpl;
import by.itacademy.entities.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */
public class AuthorDAOImplTest {
    private AuthorDAO authorDAO;
    private Author author;

    @Before
    public void createAuthor() {
        authorDAO = AuthorDAOImpl.getInstance();
        author = new Author();
        author.setName("Иван");
        author.setSecondName("Иванович");
        author.setSurname("Козлов");
        author.setBirthday(LocalDate.of(1996, 12, 1));
        author.setCountry("Россия");
    }

    @Test
    public void saveAndGetBySurname() throws Exception {
        author = authorDAO.save(author);
        Author newAuthor = authorDAO.getBySurname("Козлов").get(0);
        Assert.assertEquals(author.toString(), newAuthor.toString());
        authorDAO.delete(author.getAuthorID());
    }


    @Test
    public void getAndUpdate() throws Exception {

        author = authorDAO.get(1);
        String oldSurname = author.getSurname();
        String newSurname = "Иванова";
        author.setSurname(newSurname);
        authorDAO.update(author);
        Author newAuthor = authorDAO.get(author.getAuthorID());
        Assert.assertEquals(author.getSurname(), newAuthor.getSurname());
        newAuthor.setSurname(oldSurname);
        authorDAO.update(newAuthor);
    }

    @Test
    public void getAllAndDelete() throws Exception {

        authorDAO.save(author);
        List<Author> authors = authorDAO.getAll();
        int oldSize = authors.size();
        authorDAO.delete(author.getAuthorID());
        authors = authorDAO.getAll();
        Assert.assertEquals(oldSize - 1, authors.size());
    }
}