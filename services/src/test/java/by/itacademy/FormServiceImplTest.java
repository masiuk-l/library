package by.itacademy;

import by.itacademy.impl.FormServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 */
public class FormServiceImplTest {
    private FormService formService;
    private Form form;

    @Before
    public void createForm() {
        formService = FormServiceImpl.getInstance();
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
        form = formService.save(form);
        Form newForm = formService.getByReceivalType("Формуляр").get(0);
        Assert.assertEquals(form.toString(), newForm.toString());
        formService.delete(newForm.getFormID());
    }


    @Test
    public void getAndUpdate() throws Exception {
        form = formService.save(form);
        form.setReceivalType("Формулярpp");
        formService.update(form);
        Form newForm = formService.get(form.getFormID());
        Assert.assertEquals(form.toString(), newForm.toString());
        formService.delete(form.getFormID());
    }

    @Test
    public void getAllAndDelete() throws Exception {
        form = formService.save(form);
        List<Form> forms = formService.getAll();
        int oldSize = forms.size();
        formService.delete(form.getFormID());
        forms = formService.getAll();
        Assert.assertEquals(oldSize - 1, forms.size());
    }

}