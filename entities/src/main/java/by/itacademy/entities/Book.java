package by.itacademy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Integer bookID;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    private Set<Author> authors = new HashSet<>(0);

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Form> forms = new HashSet<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (bookID != null ? !bookID.equals(book.bookID) : book.bookID != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        if (year != null ? !year.equals(book.year) : book.year != null) return false;
        return quantity != null ? quantity.equals(book.quantity) : book.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bookID != null ? bookID.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", quantity=" + quantity +
                '}';
    }
}
