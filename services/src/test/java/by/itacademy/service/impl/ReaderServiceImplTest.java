package by.itacademy.service.impl;

import by.itacademy.dao.auth.Encoder;
import by.itacademy.entities.Reader;
import by.itacademy.service.ReaderService;
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
public class ReaderServiceImplTest {
    @Autowired
    ReaderService readerService;
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
        reader = readerService.save(reader);
        reader.setPassword(Encoder.encode(reader.getPassword()));
        Reader newReader = readerService.getByLogin("ffr@ww");
        Assert.assertEquals(reader.getName(), newReader.getName());
        readerService.delete(reader.getReaderID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        readerService.save(reader);
        String newSurname = "Иванова";
        reader = readerService.getByLogin("ffr@ww");
        Reader newReader = readerService.get(1);
        newReader.setSurname(newSurname);
        readerService.update(reader, newReader);
        Reader readerFromDb = readerService.get(reader.getReaderID());
        Assert.assertEquals(newSurname, readerFromDb.getSurname());
        readerService.delete(reader.getReaderID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        readerService.save(reader);
        List<Reader> readers = readerService.getAll();
        int oldSize = readers.size();
        readerService.delete(reader.getReaderID());
        readers = readerService.getAll();
        Assert.assertEquals(oldSize - 1, readers.size());
    }


}