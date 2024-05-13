package com.doubledown.assignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestMain {

    public static void main(String[] args) {
        String dateTimeString = "Mon, 13 May 2024 17:50:00 +0900";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        System.out.println(dateTime);
    }

}
