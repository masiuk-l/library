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
@Entity
@Table(name = "READER")
public class Reader extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "READER_ID")
    private Integer readerID;

    //    @PastOrPresent(message = "Birthday must be a past date")
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "reader")
    private Set<Form> forms = new HashSet<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (readerID != null ? !readerID.equals(reader.readerID) : reader.readerID != null) return false;
        if (birthday != null ? !birthday.equals(reader.birthday) : reader.birthday != null) return false;
        if (gender != null ? !gender.equals(reader.gender) : reader.gender != null) return false;
        return status != null ? status.equals(reader.status) : reader.status == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (readerID != null ? readerID.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerID=" + readerID +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
