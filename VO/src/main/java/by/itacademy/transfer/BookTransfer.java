package by.itacademy.transfer;

import by.itacademy.BookVO;
import by.itacademy.Author;
import by.itacademy.Book;
import by.itacademy.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * Project KR. Created by masiuk-l on 17.08.2017.
 * <p>
 * Static methods to transform Book to BookVO and vice versa
 */
public class BookTransfer {
    public static BookVO toValueObject(Book book, List<Reader> readers, List<Author> authors) {
        BookVO bookVO = new BookVO();
        bookVO.setBook(book);
        bookVO.setAuthors(new ArrayList<>(authors));
        bookVO.setReaders(new ArrayList<>(readers));
        return bookVO;
    }

    public static Book toEntity(BookVO bookVO) {
        Book book = bookVO.getBook();
        return book;
    }
}
