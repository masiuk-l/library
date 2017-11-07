package by.itacademy.service.impl;

import by.itacademy.entities.Author;
import by.itacademy.service.AuthorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class AuthorServiceImplTest {
    @Autowired
    AuthorService authorService;
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
        author = authorService.save(author);
        Author newAuthor = authorService.getAll().get(0);
        Assert.assertEquals(author.toString(), newAuthor.toString());
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
        Assert.assertEquals("Иванова", newAuthor.getSurname());
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

}