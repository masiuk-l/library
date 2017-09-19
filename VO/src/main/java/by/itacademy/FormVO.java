package by.itacademy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project KR. Created by masiuk-l on 17.08.2017.
 * <p>
 * Value object of Form entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FormVO {
    private Form form;
    private Reader reader;
    private Librarian librarian;
    private BookVO bookVO;
}
