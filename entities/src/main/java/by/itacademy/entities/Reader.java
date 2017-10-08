package by.itacademy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "READER_ID")
    private Integer readerID;
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private Set<Form> forms = new HashSet<>(0);
}
