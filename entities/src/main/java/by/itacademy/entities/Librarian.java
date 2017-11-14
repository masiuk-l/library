package by.itacademy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LIBRARIAN")
public class Librarian extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIBRARIAN_ID")
    private Integer librarianID;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "librarian")
    private Set<Form> forms = new HashSet<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Librarian librarian = (Librarian) o;

        return librarianID != null ? librarianID.equals(librarian.librarianID) : librarian.librarianID == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (librarianID != null ? librarianID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "librarianID=" + librarianID +
                '}';
    }
}
