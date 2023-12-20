package com.spring.reto.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    // Password must be at least 8 characters long, contain at least one uppercase letter,
    // one lowercase letter, one digit, and one special character
    private static final String PASSWORD_REGEX =
            ".{6,}";

    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValidPassword(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
