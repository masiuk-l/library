package by.itacademy.dao.impl;

import by.itacademy.dao.LibrarianDAO;
import by.itacademy.entities.Librarian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional(transactionManager = "txManager")
public class LibrarianDAOImplTest {
    @Autowired
    LibrarianDAO librarianDAO;
    private Librarian librarian;

    @Before
    public void createLibrarian() {
        librarian = new Librarian();
        librarian.setName("Иван");
        librarian.setSecondName("Иванович");
        librarian.setSurname("Иванов");
        librarian.setEmail("ffr@ww");
        librarian.setPassword("fvfdcsdv");
    }

    @Test
    public void saveAndGetBySurname() throws Exception {
        librarian = librarianDAO.save(librarian);
        Librarian newLibrarian = librarianDAO.getByLogin("ffr@ww").get(0);
        librarian.setPassword(librarian.getPassword());
        Assert.assertEquals(librarian.getName(), newLibrarian.getName());
        librarianDAO.delete(newLibrarian.getLibrarianID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        librarianDAO.save(librarian);
        String newSurname = "Иванова";
        librarian = librarianDAO.getByLogin("ffr@ww").get(0);
        librarian.setSurname(newSurname);
        librarianDAO.update(librarian);
        Librarian newLibrarian = librarianDAO.get(librarian.getLibrarianID());
        Assert.assertTrue(librarian.equals(newLibrarian));
        librarianDAO.delete(librarian.getLibrarianID());
    }

    @Test
    public void getAllAndDelete() throws Exception {

        librarianDAO.save(librarian);
        List<Librarian> librarians = librarianDAO.getAll();
        int oldSize = librarians.size();
        librarianDAO.delete(librarian.getLibrarianID());
        librarians = librarianDAO.getAll();
        Assert.assertEquals(oldSize - 1, librarians.size());
    }

}