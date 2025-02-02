package com.tutorialsninja.qa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String generateTimestamp() {
        // Timestamp to create dynamic email ID
        Date date = new Date();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
        return "test" + timestamp + "@gmail.com";
    }
}
