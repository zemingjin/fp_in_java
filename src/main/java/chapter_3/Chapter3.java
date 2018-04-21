package chapter_3;

import model.Function;

import java.util.regex.Pattern;

public class Chapter3 {
    private final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    final Function<String, Boolean> emailChecker = s -> emailPattern.matcher(s).matches();

    void testMail(String email) {
        if (emailChecker.apply(email)) {
            sendVerificationMail(email);
        } else {
            logError("email " + email + " is invalid.");
        }
    }
    void sendVerificationMail(String s) {
        System.out.println("Verification mail sent to " + s);
    }
    private static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }
}
