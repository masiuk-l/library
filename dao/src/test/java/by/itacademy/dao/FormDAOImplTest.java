package by.itacademy.dao;

import by.itacademy.dao.impl.FormDAOImpl;
import by.itacademy.entities.Form;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 10.08.2017.
 */
public class FormDAOImplTest {
    private FormDAO formDAO;
    private Form form;

    @Before
    public void createForm() {
        formDAO = FormDAOImpl.getInstance();
        form = new Form();
        form.setBookID(5);
        form.setReaderID(3);
        form.setLibrarianID(2);
        form.setReceivalType("Формуляр");
        form.setReceivalDate(LocalDate.now());
        form.setReturnDate(LocalDate.now().plus(14, ChronoUnit.DAYS));
    }

    @Test
    public void saveAndGetByReceivalType() throws Exception {
        form = formDAO.save(form);
        Form newForm = formDAO.getByReceivalType("Формуляр").get(0);
        Assert.assertEquals(form.toString(), newForm.toString());
        formDAO.delete(newForm.getFormID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        form = formDAO.save(form);
        form.setReceivalType("Формулярpp");
        formDAO.update(form);
        Form newForm = formDAO.get(form.getFormID());
        Assert.assertEquals(form.toString(), newForm.toString());
        formDAO.delete(form.getFormID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        form = formDAO.save(form);
        List<Form> forms = formDAO.getAll();
        int oldSize = forms.size();
        formDAO.delete(form.getFormID());
        forms = formDAO.getAll();
        Assert.assertEquals(oldSize - 1, forms.size());
    }

}