package by.itacademy.service.impl;

import by.itacademy.entities.Librarian;
import by.itacademy.service.LibrarianService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class LibrarianServiceImplTest {
    @Autowired
    LibrarianService librarianService;
    private Librarian librarian;

    @Before
    public void createLibrarian() {
        librarian = new Librarian();
        librarian.setName("Иван");
        librarian.setSecondName("Иванович");
        librarian.setSurname("Иванов");
        librarian.setEmail("ffr@ww");
        librarian.setPassword("fvfdcsdv");
        librarian = librarianService.save(librarian);
    }

    @Test
    public void saveAndGetBySurname() throws Exception {

        Librarian newLibrarian = librarianService.getByLogin("ffr@ww");
        librarian.setPassword(librarian.getPassword());
        Assert.assertEquals(librarian.getName(), newLibrarian.getName());
        librarianService.delete(newLibrarian.getLibrarianID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        String newSurname = "Иванова";
        librarian = librarianService.getByLogin("ffr@ww");
        librarian.setSurname(newSurname);
        librarianService.update(librarian);
        Librarian newLibrarian = librarianService.get(librarian.getLibrarianID());
        Assert.assertTrue(librarian.equals(newLibrarian));
        librarianService.delete(librarian.getLibrarianID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        List<Librarian> librarians = librarianService.getAll();
        int oldSize = librarians.size();
        librarianService.delete(librarian.getLibrarianID());
        librarians = librarianService.getAll();
        Assert.assertEquals(oldSize - 1, librarians.size());
    }

}