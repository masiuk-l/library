package by.itacademy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * Project KR. Created by masiuk-l on 08.10.2017.
 * <p>
 * Superclass for Librarian and Reader
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Name must start with capital and be 1 - 30 symbols long.")
    @Column(name = "NAME")
    private String name;

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Surname must start with capital and be 1 - 30 symbols long.")
    @Column(name = "SURNAME")
    private String surname;

    //    @Pattern(regexp = "^[А-ЯЁ]([a-яё]){0,29}$", message = "Second name must start with capital and be 1 - 30 symbols long.")
    @Column(name = "SECOND_NAME")
    private String secondName;

    //    @Pattern(regexp = ".{6,30}$", message = "Password must be 6 - 30 symbols long.")
    @Column(name = "PASSWORD")
    private String password;

    //    @Email(message = "Email must be a valid address")
    @Column(name = "EMAIL")
    private String email;
}
