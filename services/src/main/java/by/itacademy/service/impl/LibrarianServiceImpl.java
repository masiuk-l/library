package by.itacademy.service.impl;


import by.itacademy.dao.LibrarianDAO;
import by.itacademy.entities.Librarian;
import by.itacademy.service.LibrarianService;
import by.itacademy.service.ServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of LibrarianService
 */

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    LibrarianDAO librarianDAO;


    @Override
    public Librarian save(Librarian librarian) {
        return librarianDAO.save(librarian);
    }

    @Override
    public Librarian get(Integer id) {
        return librarianDAO.findOne(id);
    }

    @Override
    public void update(Librarian librarian) {
        librarianDAO.save(librarian);
    }

    @Override
    public void delete(Integer id) {
        librarianDAO.delete(id);
    }

    @Override
    public List<Librarian> getAll() {
        List<Librarian> librarians = new ArrayList<>();
        librarianDAO.findAll().forEach(librarians::add);
        return librarians;
    }

    @Override
    public Librarian getByLogin(String login) {
        ArrayList<Librarian> librarians;
        librarians = Lists.newArrayList(librarianDAO.findByEmail(login));
        if (librarians.size() > 1)
            throw new ServiceException("Multiple login Error");
        if (librarians.isEmpty())
            return null;
        return librarians.get(0);

    }
}
