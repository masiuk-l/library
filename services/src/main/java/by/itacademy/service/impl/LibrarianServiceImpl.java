package by.itacademy.service.impl;


import by.itacademy.dao.DAO;
import by.itacademy.dao.LibrarianDAO;
import by.itacademy.entities.Librarian;
import by.itacademy.service.LibrarianService;
import by.itacademy.service.ServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of LibrarianService
 */

@Service
@Transactional
public class LibrarianServiceImpl extends IServiceImpl<Librarian> implements LibrarianService {

    public LibrarianServiceImpl(@Autowired DAO<Librarian, Integer> dao) {
        this.dao = dao;
    }

    @Autowired
    LibrarianDAO librarianDAO;

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
