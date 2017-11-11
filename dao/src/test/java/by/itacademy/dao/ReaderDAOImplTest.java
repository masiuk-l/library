package by.itacademy.dao;

import by.itacademy.dao.auth.Encoder;
import by.itacademy.entities.Reader;
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
 * Project KR. Created by masiuk-l on 06.08.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-context.xml")
@Transactional(transactionManager = "transactionManager")
public class ReaderDAOImplTest {
    @Autowired
    ReaderDAO readerDAO;
    private Reader reader;

    @Before
    public void createReader() {
        reader = new Reader();
        reader.setName("Иван");
        reader.setSecondName("Иванович");
        reader.setSurname("Козлов");
        reader.setBirthday(LocalDate.of(1996, 12, 1));
        reader.setEmail("ffr@ww");
        reader.setPassword("fvfdcsdv");
        reader.setGender("женский");
        reader.setStatus("");
    }

    @Test
    public void saveAndGetBySurname() throws Exception {
        reader = readerDAO.save(reader);
        reader.setPassword(Encoder.encode(reader.getPassword()));
        Reader newReader = readerDAO.findByEmail("ffr@ww").get(0);
        Assert.assertEquals(reader.getName(), newReader.getName());
        readerDAO.delete(reader.getReaderID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        readerDAO.save(reader);
        String newSurname = "Иванова";
        reader = readerDAO.findByEmail("ffr@ww").get(0);
        reader.setSurname(newSurname);
        readerDAO.save(reader);
        Reader newReader = readerDAO.findOne(reader.getReaderID());
        Assert.assertEquals(reader.getSurname(), newReader.getSurname());
        readerDAO.delete(reader.getReaderID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        readerDAO.save(reader);
        List<Reader> readers = new ArrayList<>();
        readerDAO.findAll().forEach(readers::add);
        int oldSize = readers.size();
        readerDAO.delete(reader.getReaderID());
        readers.clear();
        readerDAO.findAll().forEach(readers::add);
        Assert.assertEquals(oldSize - 1, readers.size());
    }

}