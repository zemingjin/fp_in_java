package chapter_3;

import model.Function;

import java.util.regex.Pattern;

import static chapter_3.Case.*;

public class EmailValidation {
    private static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    private static Effect<String> success = s -> System.out.println("Mail sent to " + s);
    private static Effect<String> failure = s -> System.err.println("Error message logged: " + s);

    public static void main(String... args) {
        emailChecker.apply("this.is@my.email").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
        emailChecker.apply("john.doe@acme.com").bind(success, failure);
    }

    private static Function<String, Result<String>> emailChecker = s -> match(
            mcase(() -> Result.success(s)),
            mcase(() -> s == null, () -> Result.failure("email must not be null")),
            mcase(() -> s.length() == 0, () -> Result.failure("email must not be empty")),
            mcase(() -> !emailPattern.matcher(s).matches(), () ->
                    Result.failure("email " + s + " is invalid."))
                                                                             );
}