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

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    private Set<Form> forms = new HashSet<>(0);

}
