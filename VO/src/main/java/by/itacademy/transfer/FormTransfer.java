package by.itacademy.transfer;

import by.itacademy.BookVO;
import by.itacademy.FormVO;
import by.itacademy.Form;
import by.itacademy.Librarian;
import by.itacademy.Reader;

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
