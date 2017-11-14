package by.itacademy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORM")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORM_ID")
    private Integer formID;

    @Column(name = "RECEIVAL_TYPE")
    private String receivalType;
    @Column(name = "RECEIVAL_DATE")
    private LocalDate receivalDate;
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LIBRARIAN_ID")
    private Librarian librarian;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form = (Form) o;

        if (formID != null ? !formID.equals(form.formID) : form.formID != null) return false;
        if (receivalType != null ? !receivalType.equals(form.receivalType) : form.receivalType != null) return false;
        if (receivalDate != null ? !receivalDate.equals(form.receivalDate) : form.receivalDate != null) return false;
        return returnDate != null ? returnDate.equals(form.returnDate) : form.returnDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (formID != null ? formID.hashCode() : 0);
        result = 31 * result + (receivalType != null ? receivalType.hashCode() : 0);
        result = 31 * result + (receivalDate != null ? receivalDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Form{" +
                "formID=" + formID +
                ", receivalType='" + receivalType + '\'' +
                ", receivalDate=" + receivalDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
