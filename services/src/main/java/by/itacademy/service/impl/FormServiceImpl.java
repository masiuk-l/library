package by.itacademy.service.impl;

import by.itacademy.dao.FormDAO;
import by.itacademy.entities.Book;
import by.itacademy.entities.Form;
import by.itacademy.entities.Reader;
import by.itacademy.service.FormService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 * <p>
 * Implementation of FormService
 */
@Service
@Transactional
public class FormServiceImpl implements FormService {

    @Autowired
    FormDAO formDAO;



    @Override
    public Form save(Form form) {
        return formDAO.save(form);
    }

    @Override
    public Form get(Integer id) {
        return formDAO.findOne(id);
    }

    @Override
    public void update(Form form) {
        formDAO.save(form);
    }

    @Override
    public void delete(Integer id) {
        formDAO.delete(id);
    }

    @Override
    public List<Form> getAll() {
        return Lists.newArrayList(formDAO.findAll());
    }

    @Override
    public List<Form> getByReader(Reader reader) {
        ArrayList<Form> forms;
        forms = Lists.newArrayList(formDAO.findByReader(reader));
        return forms;
    }


    @Override
    public List<Form> getByBook(Book book) {
        ArrayList<Form> forms;
        forms = Lists.newArrayList(formDAO.findByBook(book));
        return forms;
    }
}
