package dev.belavirag.assignment.todo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utils {
    public static java.util.Date toJavaDate(LocalDate localDate) {
        ZonedDateTime dateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = dateTime.toInstant();
        return java.util.Date.from(instant);
    }

    public static java.sql.Date toSQLDate(LocalDate localDate) {
        ZonedDateTime dateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = dateTime.toInstant();
        return new java.sql.Date(toJavaDate(localDate).getTime());
    }
}
