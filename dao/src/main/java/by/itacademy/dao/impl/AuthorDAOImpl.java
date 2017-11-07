package by.itacademy.dao.impl;

import by.itacademy.dao.AuthorDAO;
import by.itacademy.entities.Author;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

/**
 * Project KR. Created by masiuk-l on 07.08.2017.
 * <p>
 * Implementation of AuthorDAO interface
 */

@Log4j
@Repository
public class AuthorDAOImpl extends BaseDAOImpl<Author> implements AuthorDAO {
}
