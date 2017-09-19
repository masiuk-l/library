package by.itacademy;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 15.08.2017.
 */
public interface FormService extends Service<Form> {


    /**
     * @param reader reader
     * @return List of forms matching the input
     */
    List<Form> getByReader(Reader reader);

    /**
     * @param librarian librarian
     * @return List of forms matching the input
     */
    List<Form> getByLibrarian(Librarian librarian);

    /**
     * @param book book
     * @return List of forms matching the input
     */
    List<Form> getByBook(Book book);

    /**
     * @param receivalType receivalType
     * @return List of forms matching the input
     */
    List<Form> getByReceivalType(String receivalType);

    /**
     * @param form form
     * @return Value object of the form
     */
    FormVO getFormVO(Form form);

}
