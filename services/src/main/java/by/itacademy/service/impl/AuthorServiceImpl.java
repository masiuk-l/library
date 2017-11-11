package by.itacademy.service.impl;

import by.itacademy.dao.AuthorDAO;
import by.itacademy.entities.Author;
import by.itacademy.service.AuthorService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of AuthorService
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDAO authorDAO;

    @Override
    public Author save(Author author) {
        return authorDAO.save(author);
    }

    @Override
    public Author get(Integer id) {
        return authorDAO.findOne(id);
    }

    @Override
    public void update(Author author) {
        authorDAO.save(author);
    }

    @Override
    public void delete(Integer id) {
        authorDAO.delete(id);
    }

    @Override
    public List<Author> getAll() {
        return Lists.newArrayList(authorDAO.findAll());
    }
}
