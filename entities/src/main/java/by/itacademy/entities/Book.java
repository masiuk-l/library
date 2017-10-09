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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    private Set<Author> authors = new HashSet<>(0);

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Form> forms = new HashSet<>(0);
}
