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

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Name must start with capital and be 1 - 30 symbols long.")
    @Column(name = "NAME")
    private String name;

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Surname must start with capital and be 1 - 30 symbols long.")
    @Column(name = "SURNAME")
    private String surname;

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Second name must start with capital and be 1 - 30 symbols long.")
    @Column(name = "SECOND_NAME")
    private String secondName;

    //    @PastOrPresent(message = "Birthday must be a past date")
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Country name must start with capital and be 1 - 30 symbols long.")
    @Column(name = "COUNTRY")
    private String country;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorID != null ? !authorID.equals(author.authorID) : author.authorID != null) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        if (surname != null ? !surname.equals(author.surname) : author.surname != null) return false;
        if (secondName != null ? !secondName.equals(author.secondName) : author.secondName != null) return false;
        if (birthday != null ? !birthday.equals(author.birthday) : author.birthday != null) return false;
        return country != null ? country.equals(author.country) : author.country == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (authorID != null ? authorID.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID=" + authorID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                '}';
    }
}
