package by.itacademy.dao.impl;

import by.itacademy.dao.AuthorDAO;
import by.itacademy.entities.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional(transactionManager = "txManager")
public class AuthorDAOImplTest {
    @Autowired
    AuthorDAO authorDAO;
    private Author author;

    @Before
    public void createAuthor() {
        author = new Author();
        author.setName("Иван");
        author.setSecondName("Иванович");
        author.setSurname("Козлов");
        author.setBirthday(LocalDate.of(1996, 12, 1));
        author.setCountry("Россия");
    }

    @Test
    public void saveAndGet() throws Exception {
        author = authorDAO.save(author);
        Author newAuthor = authorDAO.getAll().get(0);
        Assert.assertEquals(author.toString(), newAuthor.toString());
        authorDAO.delete(author.getAuthorID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        author = authorDAO.save(author);
        String oldSurname = author.getSurname();
        String newSurname = "Иванова";
        author.setSurname(newSurname);
        authorDAO.update(author);
        Author newAuthor = authorDAO.get(author.getAuthorID());
        Assert.assertEquals(author.getSurname(), newAuthor.getSurname());
        newAuthor.setSurname(oldSurname);
        authorDAO.update(newAuthor);
        authorDAO.delete(author.getAuthorID());
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