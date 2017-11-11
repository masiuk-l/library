package by.itacademy.service.impl;

import by.itacademy.dao.ReaderDAO;
import by.itacademy.dao.auth.Encoder;
import by.itacademy.entities.Reader;
import by.itacademy.service.ReaderService;
import by.itacademy.service.ServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of ReaderService
 */
@Service
@Transactional
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    ReaderDAO readerDAO;

    @Override
    public Reader save(Reader reader) {
        return readerDAO.save(reader);
    }

    @Override
    public Reader get(Integer id) {
        return readerDAO.findOne(id);
    }

    @Override
    public void update(Reader reader) {
        readerDAO.save(reader);
    }

    @Override
    public void delete(Integer id) {
        readerDAO.delete(id);
    }

    @Override
    public List<Reader> getAll() {
        return Lists.newArrayList(readerDAO.findAll());
    }
    @Override
    public void update(Reader oldReader, Reader newReader) {
        Reader reader = new Reader();
        reader.setReaderID(oldReader.getReaderID());
        reader.setSurname((newReader.getSurname().length() == 0) ? oldReader.getSurname() : newReader.getSurname());
        reader.setName((newReader.getName().length() == 0) ? oldReader.getName() : newReader.getName());
        reader.setSecondName((newReader.getSecondName().length() == 0) ? oldReader.getSecondName() : newReader.getSecondName());
        reader.setEmail((newReader.getEmail().length() == 0) ? oldReader.getEmail() : newReader.getEmail());
        reader.setPassword((newReader.getPassword().length() == 0) ? oldReader.getPassword() : Encoder.encode(newReader.getPassword()));
        reader.setBirthday(newReader.getBirthday());
        reader.setGender(newReader.getGender());
        reader.setStatus(oldReader.getStatus());
        update(reader);
    }

    @Override
    public Reader getByLogin(String login) {
        ArrayList<Reader> readers;
        readers = Lists.newArrayList(readerDAO.findByEmail(login));
        if (readers.size() > 1)
            throw new ServiceException("Multiple login Error");
        if (readers.isEmpty())
            return null;
        return readers.get(0);
    }
}
