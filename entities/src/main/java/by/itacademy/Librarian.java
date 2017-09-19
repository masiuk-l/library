package by.itacademy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Librarian {
    private int librarianID;
    private String name;
    private String surname;
    private String secondName;
    private String password;
    private String email;
}
