package by.itacademy.dao;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-context.xml")
@Transactional(transactionManager = "transactionManager")
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
        Author newAuthor = authorDAO.findAll().iterator().next();
        Assert.assertEquals(author.toString(), newAuthor.toString());
        authorDAO.delete(author.getAuthorID());
    }
//

    @Test
    public void getAndUpdate() throws Exception {
        author = authorDAO.save(author);
        String oldSurname = author.getSurname();
        String newSurname = "Иванова";
        author.setSurname(newSurname);
        authorDAO.save(author);
        Author newAuthor = authorDAO.findOne(author.getAuthorID());
        Assert.assertEquals(author.getSurname(), newAuthor.getSurname());
        newAuthor.setSurname(oldSurname);
        authorDAO.save(newAuthor);
        authorDAO.delete(author.getAuthorID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        authorDAO.save(author);
        List<Author> authors = new ArrayList<>();
        authorDAO.findAll().forEach(authors::add);
        int oldSize = authors.size();
        authorDAO.delete(author.getAuthorID());
        authors.clear();
        authorDAO.findAll().forEach(authors::add);
        Assert.assertEquals(oldSize - 1, authors.size());
    }

}