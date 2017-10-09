package by.itacademy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private Integer authorID;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;
    @Column(name = "COUNTRY")
    private String country;

    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Book> books = new HashSet<>(0);
}
