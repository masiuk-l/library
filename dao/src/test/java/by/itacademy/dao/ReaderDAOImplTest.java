package by.itacademy.dao;

import by.itacademy.dao.auth.Encoder;
import by.itacademy.dao.impl.ReaderDAOImpl;
import by.itacademy.entities.Reader;
import by.itacademy.util.HibernateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public class ReaderDAOImplTest {
    private ReaderDAO readerDAO;
    private Reader reader;

    @Before
    public void createReader() {
        readerDAO = ReaderDAOImpl.getInstance();
        reader = new Reader();
        reader.setName("Иван");
        reader.setSecondName("Иванович");
        reader.setSurname("Козлов");
        reader.setBirthday(LocalDate.of(1996, 12, 1));
        reader.setEmail("ffr@ww");
        reader.setPassword("fvfdcsdv");
        reader.setGender("женский");
        reader.setStatus("");
        HibernateUtil.getEntityManager();
        HibernateUtil.beginTransaction();
    }

    @Test
    public void saveAndGetBySurname() throws Exception {
        reader = readerDAO.save(reader);
        reader.setPassword(Encoder.encode(reader.getPassword()));
        Reader newReader = readerDAO.getBySurname("Козлов").get(0);
        Assert.assertEquals(reader.getName(), newReader.getName());
        readerDAO.delete(reader.getReaderID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        readerDAO.save(reader);
        String newSurname = "Иванова";
        reader = readerDAO.getBySurname("Козлов").get(0);
        reader.setSurname(newSurname);
        readerDAO.update(reader);
        Reader newReader = readerDAO.get(reader.getReaderID());
        Assert.assertEquals(reader.getSurname(), newReader.getSurname());
        readerDAO.delete(reader.getReaderID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        readerDAO.save(reader);
        List<Reader> readers = readerDAO.getAll();
        int oldSize = readers.size();
        readerDAO.delete(reader.getReaderID());
        readers = readerDAO.getAll();
        Assert.assertEquals(oldSize - 1, readers.size());
    }

    @After
    public void tearDown() {
        HibernateUtil.commit();
        HibernateUtil.closeEntityManager();
    }
}