package by.itacademy.impl;

import by.itacademy.*;
import by.itacademy.auth.Encoder;
import by.itacademy.transfer.ReaderTransfer;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 * <p>
 * Implementation of ReaderService
 */
public class ReaderServiceImpl extends AbstractService implements ReaderService {

    private static volatile ReaderService INSTANCE = null;

    private ReaderDAO readerDAO = ReaderDAOImpl.getInstance();
    private FormDAO formDAO = FormDAOImpl.getInstance();
    private FormService formService = FormServiceImpl.getInstance();

    private ReaderServiceImpl() {
    }

    public static ReaderService getInstance() {
        ReaderService readerService = INSTANCE;
        if (readerService == null) {
            synchronized (ReaderServiceImpl.class) {
                readerService = INSTANCE;
                if (readerService == null) {
                    INSTANCE = readerService = new ReaderServiceImpl();
                }
            }
        }

        return readerService;
    }

    @Override
    public Reader save(Reader reader) {
        try {
            if (reader != null) {
                startTransaction();
                reader = readerDAO.save(reader);
                commit();
                return reader;
            } else {
                throw new ServiceException("Reader not defined");
            }
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Reader", e);
        }

    }

    @Override
    public Reader get(Serializable id) {
        try {
            Reader reader;
            startTransaction();
            reader = readerDAO.get(id);
            commit();
            return reader;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Reader", e);
        }
    }

    @Override
    public void update(Reader reader) {
        try {
            startTransaction();
            readerDAO.update(reader);
            commit();
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error updating Reader", e);
        }
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
    public int delete(Serializable id) {
        try {
            startTransaction();
            int rows = readerDAO.delete(id);
            commit();
            return rows;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error deleting Reader", e);
        }
    }

    @Override
    public List<Reader> getBySurname(String surname) {
        ArrayList<Reader> readers;
        try {
            startTransaction();
            readers = new ArrayList<>(readerDAO.getBySurname(surname));
            commit();
            return readers;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding Reader", e);
        }
    }

    @Override
    public List<Reader> getByStatus(String status) {
        ArrayList<Reader> readers;
        try {
            startTransaction();
            readers = new ArrayList<>(readerDAO.getByStatus(status));
            commit();
            return readers;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding Reader", e);
        }
    }

    @Override
    public Reader getByLogin(String login) {
        ArrayList<Reader> readers;
        try {
            startTransaction();
            readers = new ArrayList<>(readerDAO.getByLogin(login));
            if (readers.size() > 1)
                throw new ServiceException("Multiple login Error");
            commit();
            if (readers.isEmpty())
                return null;
            return readers.get(0);
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding Reader", e);
        }
    }

    @Override
    public ReaderVO getReaderVO(Reader reader) {
        try {
            startTransaction();
            ReaderVO readerVO;
            List<FormVO> formVOS = new ArrayList<>();
            List<Form> forms = formDAO.getByReader(reader);
            for (Form form : forms) {
                FormVO formVO = formService.getFormVO(form);
                formVOS.add(formVO);
            }
            readerVO = ReaderTransfer.toValueObject(reader, formVOS);
            commit();
            return readerVO;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating ReaderVO", e);
        }
    }

    @Override
    public List<Reader> getAll() {
        ArrayList<Reader> readers;
        try {
            startTransaction();
            readers = new ArrayList<>(readerDAO.getAll());
            commit();
            return readers;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error finding Reader", e);
        }
    }
}
