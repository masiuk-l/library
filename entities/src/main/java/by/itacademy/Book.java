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
public class Book {
    private int bookID;
    private String name;
    private String isbn;
    private String genre;
    private int year;
    private int quantity;
}
