package by.itacademy.VO.transfer;

import by.itacademy.VO.BookVO;
import by.itacademy.VO.FormVO;
import by.itacademy.entities.Form;
import by.itacademy.entities.Librarian;
import by.itacademy.entities.Reader;

/**
 * Project KR. Created by masiuk-l on 17.08.2017.
 *
 * Static methods to transform Form to FormVO and vice versa
 */
public class FormTransfer {
    public static FormVO toValueObject(Form form, BookVO bookVO, Librarian librarian, Reader reader) {
        FormVO formVO = new FormVO();
        formVO.setForm(form);
        formVO.setBookVO(bookVO);
        formVO.setLibrarian(librarian);
        formVO.setReader(reader);
        return formVO;
    }

    public static Form toEntity(FormVO formVO) {
        Form form = formVO.getForm();
        return form;
    }
}
