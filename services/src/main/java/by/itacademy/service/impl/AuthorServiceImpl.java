package by.itacademy.service.impl;

import by.itacademy.dao.DAO;
import by.itacademy.entities.Author;
import by.itacademy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of AuthorService
 */
@Service
@Transactional
public class AuthorServiceImpl extends IServiceImpl<Author> implements AuthorService {

    public AuthorServiceImpl(@Autowired DAO<Author, Integer> dao) {
        this.dao = dao;
    }
}
