package by.itacademy.impl;

import by.itacademy.AuthorService;
import by.itacademy.entities.Author;
import by.itacademy.util.HibernateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 */
public class AuthorServiceImplTest {
    private AuthorService authorService;
    private Author author;

    @Before
    public void createAuthor() {
        authorService = AuthorServiceImpl.getInstance();
        author = new Author();
        author.setName("Иван");
        author.setSecondName("Иванович");
        author.setSurname("Козлов");
        author.setBirthday(LocalDate.of(1996, 12, 1));
        author.setCountry("Россия");
        HibernateUtil.getEntityManager("by.itacademy.test");
    }

    @Test
    public void saveAndGetBySurname() throws Exception {
        author = authorService.save(author);
//        Author newAuthor = authorService.getBySurname("Козлов").get(0);
//        Assert.assertEquals(author.toString(), newAuthor.toString());
        authorService.delete(author.getAuthorID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        author = authorService.save(author);
        String oldSurname = author.getSurname();
        String newSurname = "Иванова";
        author.setSurname(newSurname);
        authorService.update(author);
        Author newAuthor = authorService.get(author.getAuthorID());
        Assert.assertEquals(author.getSurname(), newAuthor.getSurname());
        newAuthor.setSurname(oldSurname);
        authorService.update(newAuthor);
        authorService.delete(author.getAuthorID());
    }

    @Test
    public void getAllAndDelete() throws Exception {

        authorService.save(author);
        List<Author> authors = authorService.getAll();
        int oldSize = authors.size();
        authorService.delete(author.getAuthorID());
        authors = authorService.getAll();
        Assert.assertEquals(oldSize - 1, authors.size());
    }

    @After
    public void tearDown() {
        HibernateUtil.closeEntityManager();
    }

}