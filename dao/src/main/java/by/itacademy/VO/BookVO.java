package by.itacademy.VO;

import by.itacademy.entities.Author;
import by.itacademy.entities.Book;
import by.itacademy.entities.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 17.08.2017.
 * <p>
 * Value object of Book entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookVO {
    private Book book;
    private List<Author> authors = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
}
