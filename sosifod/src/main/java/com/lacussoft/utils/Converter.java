package com.lacussoft.utils;

import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.ParseException;

public class Converter {

    private Converter() {}

    public static String removeNonDigits(String str) {
        if (str == null) {
            return null;
        }

        return nullable(str.replaceAll("\\D", ""));
    }

    public static Date toDate(String strDate) {
        if (strDate == null) {
            return null;
        }

        String regex = "^(\\d{1,2})/(\\d{1,2})/(\\d{4})";
        if (Pattern.compile(regex).matcher(strDate).matches()) {
            return toDate(strDate, "dd/MM/yyyy");
        }

        regex = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})";
        if (Pattern.compile(regex).matcher(strDate).matches()) {
            return toDate(strDate, "yyyy-MM-dd");
        }

        return null;
    }

    public static Date toDate(String strDate, String format) {
        try {
            return new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException | NullPointerException ex) {
            return null;
        }
    }

    public static LocalDate toLocalDate(String strDate) {
        if (strDate == null) {
            return null;
        }

        String regex = "^(\\d{1,2})/(\\d{1,2})/(\\d{4})";
        if (Pattern.compile(regex).matcher(strDate).matches()) {
            return toLocalDate(strDate, "dd/MM/yyyy");
        }

        regex = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})";
        if (Pattern.compile(regex).matcher(strDate).matches()) {
            return toLocalDate(strDate, "yyyy-MM-dd");
        }

        return null;
    }

    public static LocalDate toLocalDate(String strDate, String format) {
        try {
            return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException | NullPointerException ex) {
            System.out.println("sim");
            return null;
        }
    }

    public static String toCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        return nullable(cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
    }

    public static String toPhone(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }

        return nullable(phoneNumber.replaceAll("(\\d{2})(\\d{4,5})(\\d{4})", "($1) $2-$3"));
    }

    public static String nullable(String str) {
        if (str == null) {
            return null;
        }

        String trimmed = str.trim();
        if (trimmed.equals("")) {
            return null;
        }

        return trimmed;
    }
}
