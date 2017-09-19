package by.itacademy.impl;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 *
 * contains util methods of date conversion
 */

public abstract class AbstractDAO {
    /**
     * obtains date in LocalDate format and returns SQL Date
     *
     * @param localDate date in LocalDate format
     * @return SQL representation of date
     */
    public Date toSQLDate(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return new Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * obtains date in SQL Date format and returns LocalDate
     * @param date date in SQL Date format
     * @return LocalDate representation of date
     */
    public LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
